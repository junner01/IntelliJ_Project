package com.busanit501.helloworld.member;

import com.busanit501.helloworld.member.dao.MemberDAO;
import com.busanit501.helloworld.member.vo.MemberVO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;


public class MemberDAOTest {
    private MemberDAO memberDAO;


    @BeforeEach
    public void ready() {
        memberDAO = new MemberDAO();
    }

    @Test
    public void getTime() {
        System.out.println("sql 전달 후, 시간 조회 확인용: " + memberDAO.getTime());
    }

    @Test
    public void getTime2() throws SQLException {
        System.out.println("sql 전달 후, " +
                "시간 조회 확인용: 자동 반납 @Cleanup 확인 " + memberDAO.getTime2());
    }

    @Test
    public void insetTest() throws Exception {
        MemberVO memberVO = MemberVO.builder()
                .title("Test 데이터 추가")
                .dueDate(LocalDate.now())
                .finished(false)
                .build();
        memberDAO.insert(memberVO);
    }

    // 전체 목록 조회 테스트
    @Test
    public void testList() throws SQLException {
        List<MemberVO> list = memberDAO.selectAll();
        list.forEach(vo -> System.out.println(vo));
    }

    // 하나 조회 테스트
    @Test
    public void getOneTest() throws SQLException {
        Long mno = 3L;
        MemberVO memberVO = memberDAO.selectOne(mno);
        System.out.println(memberVO);
    }

    // 삭제 테스트
    @Test
    public void deleteTest() throws SQLException {
        Long mno = 4L;
        memberDAO.deleteMember(mno);
    }

    // 수정 테스트
    @Test
    public void updateTest() throws SQLException {

        MemberVO memberVO = MemberVO.builder()
                .mno(3L)
                .title("수정 테스트 중")
                .finished(true)
                .dueDate(LocalDate.of(2024, 11, 25))
                .build();

        memberDAO.updateMember(memberVO);

    }
}
