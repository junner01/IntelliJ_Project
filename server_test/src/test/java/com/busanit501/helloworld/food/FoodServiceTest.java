package com.busanit501.helloworld.food;

import com.busanit501.helloworld.food.dto.FoodDTO;
import com.busanit501.helloworld.food.service.FoodService;

import com.busanit501.helloworld.jdbcex.dto.TodoDTO;
import lombok.extern.log4j.Log4j2;
import lombok.val;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

@Log4j2
public class FoodServiceTest {
    private FoodService foodService;

    @BeforeEach
    public void ready() {
        foodService = FoodService.INSTANCE;
    }

    // 등록
    @Test
    public void testInsert() throws SQLException {
        // 더미 데이터, 화면에서 전달 받은 FoodDTO
        FoodDTO foodDTO = FoodDTO.builder()
                .title("샘플 작업 1126")
                .dueDate(LocalDate.now())
                .build();
        foodService.register(foodDTO);
    }

    // 전체 조회
    @Test
    public void testSelectAll() throws SQLException {
         List<FoodDTO> dtoList = foodService.listAll();
        for (FoodDTO foodDto:dtoList) {
            log.info(foodDto);
        }
    }

    // 하나조회, 상세보기.
    @Test
    public void testSelectOne() throws SQLException {
        val foodDTO = foodService.get(3L);
        log.info("하나 조회. foodDTO " + foodDTO);
    }

    // 하나수정,
    @Test
    public void testUpdateOne() throws SQLException {

        FoodDTO foodDTO = FoodDTO.builder()
                .fno(13L)
                .title("수정된 내용입니다.")
                .dueDate(LocalDate.now())
                .finished(false)
                .build();

        foodService.update(foodDTO);
    }

    // 하나삭제,
    @Test
    public void testDelteOne() throws SQLException {
        foodService.delete(18L);
    }

}
