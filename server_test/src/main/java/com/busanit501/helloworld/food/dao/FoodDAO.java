package com.busanit501.helloworld.food.dao;

import com.busanit501.helloworld.food.vo.FoodVO;
import com.busanit501.helloworld.jdbcex.dao.ConnectionUtil;
import com.busanit501.helloworld.jdbcex.vo.TodoVO;
import lombok.Cleanup;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FoodDAO {

    // Todo 등록기능, 추가하기.
    // VO(Value Object, 실제 디비 컬럼과 일치함)
    // 서비스 계층에서, VO 넘겨 받은 데이터 중에서, 보여줄 데이터만 따로 분리해서,
    // 전달하는 용도로 사용하는 DTO 입니다.

    public void insert(FoodVO foodVO) throws SQLException {

        String sql = "insert into tbl_food (title, dueDate, finished) " +
                "values (?, ?, ?)";
        @Cleanup Connection connection = ConnectionUtil.INSTANCE.getConnection();
        @Cleanup PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, foodVO.getTitle());
        preparedStatement.setDate(2, Date.valueOf(foodVO.getDueDate()));
        preparedStatement.setBoolean(3, foodVO.isFinished());
        preparedStatement.executeUpdate();
    } //insert

    public String getTime() {
        String now = null;
        try (Connection connection = ConnectionUtil.INSTANCE.getConnection();
             PreparedStatement preparedStatement =
                     connection.prepareStatement("select now()");
             ResultSet resultSet = preparedStatement.executeQuery();
        ) {
            resultSet.next();
            now = resultSet.getString(1);

        } catch (Exception e) {
            e.printStackTrace();
        } //catch
        return now;
    } //getTime

    public String getTime2() throws SQLException {
        String now = null;
        @Cleanup Connection connection = ConnectionUtil.INSTANCE.getConnection();
        PreparedStatement preparedStatement =
                connection.prepareStatement("select now()");
        ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();
        now = resultSet.getString(1);
        return now;
    }
    // 전체 조회.
    public List<FoodVO> selectAll() throws SQLException {

        String sql = "select * from tbl_food";

        @Cleanup Connection connection = ConnectionUtil.INSTANCE.getConnection();
        @Cleanup PreparedStatement preparedStatement = connection.prepareStatement(sql);
        @Cleanup ResultSet resultSet = preparedStatement.executeQuery();

        List<FoodVO> list = new ArrayList<>();
        while (resultSet.next()) {

            FoodVO foodVO = FoodVO.builder()
                    .fno(resultSet.getLong("fno"))
                    .title(resultSet.getString("title"))
                    .dueDate(resultSet.getDate("dueDate").toLocalDate())
                    .finished(resultSet.getBoolean("finished"))
                    .build();
            list.add(foodVO);
        }
        return list;
    }
    // 하나 조회. 상세보기.
    public FoodVO selectOne(Long fno) throws SQLException {

        String sql = "select * from tbl_food where fno = ?";

        @Cleanup Connection connection = ConnectionUtil.INSTANCE.getConnection();
        @Cleanup PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setLong(1, fno);

        @Cleanup ResultSet resultSet = preparedStatement.executeQuery();

        resultSet.next();
        FoodVO fondVO = FoodVO.builder()
                .fno(resultSet.getLong("fno"))
                .title(resultSet.getString("title"))
                .dueDate(resultSet.getDate("dueDate").toLocalDate())
                .finished(resultSet.getBoolean("finished"))
                .build();
        return fondVO;
    }

    // update,
    public void updateFood(FoodVO foodVO) throws SQLException {
        String sql = " update tbl_food set title=?, dueDate=?, finished=?" +
                " where fno=?";
        @Cleanup Connection connection = ConnectionUtil.INSTANCE.getConnection();
        @Cleanup PreparedStatement preparedStatement = connection.prepareStatement(sql);

        preparedStatement.setString(1, foodVO.getTitle());
        preparedStatement.setDate(2, Date.valueOf(foodVO.getDueDate()));
        preparedStatement.setBoolean(3,foodVO.isFinished());
        preparedStatement.setLong(4,foodVO.getFno());
        preparedStatement.executeUpdate();

    }

    //삭제,
    public void deleteFood(Long fno) throws SQLException {
        String sql = "delete from tbl_food where fno =?";
        @Cleanup Connection connection = ConnectionUtil.INSTANCE.getConnection();
        @Cleanup PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setLong(1, fno);
        preparedStatement.executeUpdate();

    }


} //class

