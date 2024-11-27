package com.busanit501.helloworld.member.dao;

import com.busanit501.helloworld.member.vo.MemberVO;
import com.busanit501.helloworld.jdbcex.dao.ConnectionUtil;
import lombok.Cleanup;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MemberDAO {


    public void insert(MemberVO memberVO) throws SQLException {

        String sql = "insert into member (title, dueDate, finished) " +
                "values (?, ?, ?)";
        @Cleanup Connection connection = ConnectionUtil.INSTANCE.getConnection();
        @Cleanup PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, memberVO.getTitle());
        preparedStatement.setDate(2, Date.valueOf(memberVO.getDueDate()));
        preparedStatement.setBoolean(3, memberVO.isFinished());
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
    public List<MemberVO> selectAll() throws SQLException {

        String sql = "select * from member";

        @Cleanup Connection connection = ConnectionUtil.INSTANCE.getConnection();
        @Cleanup PreparedStatement preparedStatement = connection.prepareStatement(sql);
        @Cleanup ResultSet resultSet = preparedStatement.executeQuery();

        List<MemberVO> list = new ArrayList<>();
        while (resultSet.next()) {

            MemberVO memberVO = MemberVO.builder()
                    .mno(resultSet.getLong("mno"))
                    .title(resultSet.getString("title"))
                    .dueDate(resultSet.getDate("dueDate").toLocalDate())
                    .finished(resultSet.getBoolean("finished"))
                    .build();
            list.add(memberVO);
        }
        return list;
    }
    // 하나 조회. 상세보기.
    public MemberVO selectOne(Long fno) throws SQLException {

        String sql = "select * from member where mno = ?";

        @Cleanup Connection connection = ConnectionUtil.INSTANCE.getConnection();
        @Cleanup PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setLong(1, fno);

        @Cleanup ResultSet resultSet = preparedStatement.executeQuery();

        resultSet.next();
        MemberVO fondVO = MemberVO.builder()
                .mno(resultSet.getLong("mno"))
                .title(resultSet.getString("title"))
                .dueDate(resultSet.getDate("dueDate").toLocalDate())
                .finished(resultSet.getBoolean("finished"))
                .build();
        return fondVO;
    }

    // update,
    public void updateMember(MemberVO memberVO) throws SQLException {
        String sql = " update member set title=?, dueDate=?, finished=?" +
                " where mno=?";
        @Cleanup Connection connection = ConnectionUtil.INSTANCE.getConnection();
        @Cleanup PreparedStatement preparedStatement = connection.prepareStatement(sql);

        preparedStatement.setString(1, memberVO.getTitle());
        preparedStatement.setDate(2, Date.valueOf(memberVO.getDueDate()));
        preparedStatement.setBoolean(3,memberVO.isFinished());
        preparedStatement.setLong(4,memberVO.getMno());
        preparedStatement.executeUpdate();

    }

    //삭제,
    public void deleteMember(Long mno) throws SQLException {
        String sql = "delete from member where mno =?";
        @Cleanup Connection connection = ConnectionUtil.INSTANCE.getConnection();
        @Cleanup PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setLong(1, mno);
        preparedStatement.executeUpdate();

    }


} //class

