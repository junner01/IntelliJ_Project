package com.busanit501.helloworld.food.service;

import com.busanit501.helloworld.food.dao.FoodDAO;
import com.busanit501.helloworld.food.dto.FoodDTO;
import com.busanit501.helloworld.food.util.MapperUtil;
import com.busanit501.helloworld.food.vo.FoodVO;


import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;

import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

// 설정1
@Log4j2
public enum FoodService {
    INSTANCE;
    // 2가지, 다른 클래스에 의존함.
    // 1) 모델 맵퍼 기능
    // 2) DAO 기능

    private FoodDAO foodDAO;
    private ModelMapper modelMapper;


    FoodService() {
        foodDAO = new FoodDAO();
        modelMapper = MapperUtil.INSTANCE.get();
    }

    // register
    public void register(FoodDTO foodDTO) throws SQLException {
        FoodVO foodVO = modelMapper.map(foodDTO, FoodVO.class);
        log.info("foodVo : " + foodVO);
        foodDAO.insert(foodVO);
    }

    //2
    // 전체 조회
    public List<FoodDTO> listAll() throws SQLException {
        List<FoodVO> voList = foodDAO.selectAll();
        log.info("voList : " + voList);

        List<FoodDTO> ftoList = voList.stream().map(vo -> modelMapper.map(vo, FoodDTO.class))
                .collect(Collectors.toList());
        return ftoList;
    }

    // 하나 조회
    public FoodDTO get(Long fno) throws SQLException {
        log.info("fno : " + fno);

        FoodVO foodVO = foodDAO.selectOne(fno);
        FoodDTO foodDTO = modelMapper.map(foodVO,FoodDTO.class);

        return foodDTO;

    }
    // 수정 기능
    public void update(FoodDTO foodDTO) throws SQLException {
        log.info("foodDTO : " + foodDTO);
        FoodVO foodVO = modelMapper.map(foodDTO, FoodVO.class);
        foodDAO.updateFood(foodVO);

    }
    // 삭제 기능.
    public void delete(Long fno) throws SQLException {
        foodDAO.deleteFood(fno);
    }

}






