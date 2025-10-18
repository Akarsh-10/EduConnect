package com.wecp.progressive.dao;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.wecp.progressive.config.DatabaseConnectionManager;
import com.wecp.progressive.entity.Teacher;

public class TeacherDAOImpl implements TeacherDAO {

    Connection connection;

    public TeacherDAOImpl() {
        try
        {
            connection= DatabaseConnectionManager.getConnection();
        }
        catch(SQLException e)
        {
            System.out.println("Failed to establish a connection"+e.getMessage());
        }
    }

    @Override
    public int addTeacher(Teacher teacher) throws SQLException {
        String query="insert into teacher(full_name, subject, contact_number, email, years_of_experience) values(?,?,?,?,?)";
        try(PreparedStatement ps=connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS))
        {
            ps.setString(1, teacher.getFullName());
            ps.setString(2, teacher.getSubject());
            ps.setString(3, teacher.getContactNumber());
            ps.setString(4, teacher.getEmail());
            ps.setInt(5, teacher.getYearsOfExperience());
            
            int affectedRows= ps.executeUpdate();
            if(affectedRows>0)
            {
                try(ResultSet rs=ps.getGeneratedKeys())
                {
                    if(rs.next())
                    {
                        teacher.setTeacherId(rs.getInt(1));
                        return teacher.getTeacherId();
                    }
                }
            }
        }
        catch(SQLException e)
        {
            System.out.println(e.getMessage());
        }
        return -1;
    }

    @Override
    public void deleteTeacher(int teacherId) throws SQLException {
        String query="delete from teacher where teacher_id=?";
        try(PreparedStatement ps=connection.prepareStatement(query);)
        {
            ps.setInt(1, teacherId);
            ps.executeUpdate();
        }
        catch(SQLException e)
        {
            throw new SQLException(e.getMessage());
        }
        
    }

    @Override
    public List<Teacher> getAllTeachers() throws SQLException {
        List<Teacher> li= new ArrayList<>();
        String query="select * from teacher";
        try(Statement ps=connection.createStatement();)
        {
            try(ResultSet rs=ps.executeQuery(query);)
            {
                while(rs.next())
                {
                    li.add(new Teacher(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getInt(6)));
                }
            }
        }
        catch(SQLException e)
        {
           throw new SQLException (e.getMessage());
        }
        return li;
    }

    @Override
    public Teacher getTeacherById(int teacherId) throws SQLException {
        String query="select * from teacher where teacher_id=?";
        try(PreparedStatement ps=connection.prepareStatement(query);)
        {
            ps.setInt(1, teacherId);

            try(ResultSet rs=ps.executeQuery();)
            {
                if(rs.next())
                {
                    return new Teacher(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getInt(6));
                }
            }
        }
        catch(SQLException e)
        {
            throw new SQLException(e.getMessage());
        }
        return null;
    }

    @Override
    public void updateTeacher(Teacher teacher) throws SQLException {
        String query="update teacher set full_name=?, subject=?, contact_number=?, email=?, years_of_experience=? where teacher_id=?";
        try(PreparedStatement ps=connection.prepareStatement(query);)
        {
            ps.setString(1, teacher.getFullName());
            ps.setString(2, teacher.getSubject());
            ps.setString(3, teacher.getContactNumber());
            ps.setString(4, teacher.getEmail());
            ps.setInt(5, teacher.getYearsOfExperience());
            ps.setInt(6, teacher.getTeacherId());
            ps.executeUpdate();
        }
        catch(SQLException e)
        {
            throw new SQLException(e.getMessage());
        }
        
    }
    
}
