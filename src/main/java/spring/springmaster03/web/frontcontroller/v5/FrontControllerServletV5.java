package spring.springmaster03.web.frontcontroller.v5;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import spring.springmaster03.web.frontcontroller.ModelView;
import spring.springmaster03.web.frontcontroller.MyView;
import spring.springmaster03.web.frontcontroller.v3.controller.MemberFormControllerV3;
import spring.springmaster03.web.frontcontroller.v3.controller.MemberListControllerV3;
import spring.springmaster03.web.frontcontroller.v3.controller.MemberSaveControllerV3;
import spring.springmaster03.web.frontcontroller.v4.controller.MemberFormControllerV4;
import spring.springmaster03.web.frontcontroller.v4.controller.MemberListControllerV4;
import spring.springmaster03.web.frontcontroller.v4.controller.MemberSaveControllerV4;
import spring.springmaster03.web.frontcontroller.v5.adaptor.ControllerV3HandlerAdapter;
import spring.springmaster03.web.frontcontroller.v5.adaptor.ControllerV4HandlerAdapter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(name = "frontControllerServletV5", urlPatterns = "/front-controller/v5/*")
public class FrontControllerServletV5 extends HttpServlet {

    private final Map<String, Object> handlerMappingMap = new HashMap<>();
    private final List<MyHandlerAdaptor> handlerAdapters = new ArrayList<>();

    public FrontControllerServletV5() {
        initHandlerMappingMap();
        initHandlerAdapters();
    }

    private void initHandlerMappingMap() {
        handlerMappingMap.put("/front-controller/v5/v3/members/new-form", new MemberFormControllerV3());
        handlerMappingMap.put("/front-controller/v5/v3/members/save", new MemberSaveControllerV3());
        handlerMappingMap.put("/front-controller/v5/v3/members", new MemberListControllerV3());

        // add V4
        handlerMappingMap.put("/front-controller/v5/v4/members/new-form", new MemberFormControllerV4());
        handlerMappingMap.put("/front-controller/v5/v4/members/save", new MemberSaveControllerV4());
        handlerMappingMap.put("/front-controller/v5/v4/members", new MemberListControllerV4());
    }

    private void initHandlerAdapters() {
        handlerAdapters.add(new ControllerV3HandlerAdapter());
        handlerAdapters.add(new ControllerV4HandlerAdapter());
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // handlerMappingMap object
        Object handler = getHandler(request);
        if (handler == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        // handlerAdaptor
        MyHandlerAdaptor adapter = getHandlerAdapter(handler);

        ModelView mv = adapter.handle(request, response, handler);

        String viewName = mv.getViewName(); // logical name - new-form

        MyView view = viewResolver(viewName);
        view.render(mv.getModel(), request, response);
    }

    private MyHandlerAdaptor getHandlerAdapter(Object handler) {
        for (MyHandlerAdaptor adapter : handlerAdapters) {
            if(adapter.supports(handler)) {
                return adapter;
            }
        }
        throw new IllegalArgumentException("Cannot find handler adapter. handler=" + handler);
    }

    private Object getHandler(HttpServletRequest request) {
        String requestURI = request.getRequestURI();
        return handlerMappingMap.get(requestURI);
    }

    private MyView viewResolver(String viewName) {
        return new MyView("/WEB-INF/views/" + viewName + ".jsp");
    }
}
