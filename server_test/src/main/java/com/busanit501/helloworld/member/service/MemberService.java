package com.busanit501.helloworld.member.service;

import com.busanit501.helloworld.member.dao.MemberDAO;
import com.busanit501.helloworld.member.dto.MemberDTO;
import com.busanit501.helloworld.member.util.MapperUtil;
import com.busanit501.helloworld.member.vo.MemberVO;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;

import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

// 설정1
@Log4j2
public enum MemberService {
    INSTANCE;
    // 2가지, 다른 클래스에 의존함.
    // 1) 모델 맵퍼 기능
    // 2) DAO 기능

    private MemberDAO memberDAO;
    private ModelMapper modelMapper;


    MemberService() {
        memberDAO = new MemberDAO();
        modelMapper = MapperUtil.INSTANCE.get();
    }

    // register
    public void register(MemberDTO memberDTO) throws SQLException {
        MemberVO memberVO = modelMapper.map(memberDTO, MemberVO.class);
        log.info("memberVO : " + memberVO);
        memberDAO.insert(memberVO);
    }

    //2
    // 전체 조회
    public List<MemberDTO> listAll() throws SQLException {
        List<MemberVO> voList = memberDAO.selectAll();
        log.info("voList : " + voList);

        List<MemberDTO> ftoList = voList.stream().map(vo -> modelMapper.map(vo, MemberDTO.class))
                .collect(Collectors.toList());
        return ftoList;
    }

    // 하나 조회
    public MemberDTO get(Long fno) throws SQLException {
        log.info("fno : " + fno);

        MemberVO memberVO = memberDAO.selectOne(fno);
        MemberDTO memberDTO = modelMapper.map(memberVO,MemberDTO.class);

        return memberDTO;

    }
    // 수정 기능
    public void update(MemberDTO memberDTO) throws SQLException {
        log.info("memberDTO : " + memberDTO);
        MemberVO memberVO = modelMapper.map(memberDTO, MemberVO.class);
        memberDAO.updateMember(memberVO);

    }
    // 삭제 기능.
    public void delete(Long mno) throws SQLException {
        memberDAO.deleteMember(mno);
    }

}






