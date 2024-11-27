package com.busanit501.helloworld.member.controller;

import com.busanit501.helloworld.food.service.FoodService;
import com.busanit501.helloworld.member.dto.MemberDTO;
import com.busanit501.helloworld.member.service.MemberService;
import lombok.extern.log4j.Log4j2;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Log4j2
@WebServlet(name = "MemberUpdateController",urlPatterns = "/member/mupdate")
public class MemberUpdateController extends HttpServlet {

    private MemberService memberService = MemberService.INSTANCE;

    private final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {
            Long fno = Long.parseLong(request.getParameter("mno"));
            MemberDTO memberDTO = memberService.get(fno);

            request.setAttribute("dto", memberDTO);
            request.getRequestDispatcher("/WEB-INF/member/memberUpd.jsp")
                    .forward(request, response);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }



    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String finished = request.getParameter("finished");
        log.info("finished : " + finished);
        boolean checkFinished = false;
        if(finished.equals("on")){
            checkFinished = true;
        }
        MemberDTO memberDTO = MemberDTO.builder()
                .mno(Long.valueOf(request.getParameter("mno")))
                .title(request.getParameter("title"))
                .dueDate(LocalDate.parse(request.getParameter("dueDate"),DATE_TIME_FORMATTER))
                .finished(checkFinished)
                .build();
        log.info("memberDTO 수정된 내용: " + memberDTO);

        try {
            memberService.update(memberDTO);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        System.out.println("doPost : 글쓰기 처리하는 로직, 디비 연결 전, 리스트로 이동함");
        response.sendRedirect("/member/mlist");

    }
}
