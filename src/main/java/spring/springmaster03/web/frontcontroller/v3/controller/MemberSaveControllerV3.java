package spring.springmaster03.web.frontcontroller.v3.controller;

import spring.springmaster03.domain.member.Member;
import spring.springmaster03.domain.member.MemberRepository;
import spring.springmaster03.web.frontcontroller.ModelView;
import spring.springmaster03.web.frontcontroller.v3.ControllerV3;

import java.util.Map;

public class MemberSaveControllerV3 implements ControllerV3 {

    private MemberRepository memberRepository = MemberRepository.getInstance();

    @Override
    public ModelView process(Map<String, String> paramMap) {
        String username = paramMap.get("username");
        int age = Integer.parseInt(paramMap.get("age"));

        Member member = new Member(username, age);
        memberRepository.save(member);

        ModelView mv = new ModelView("save-result");
        mv.getModel().put("member", member);
        return mv;
    }
}
