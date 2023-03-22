package spring.springmaster03.web.frontcontroller.v3;

import spring.springmaster03.web.frontcontroller.ModelView;

import java.util.Map;

public interface ControllerV3 {

    ModelView process(Map<String, String> paramMap);
}
