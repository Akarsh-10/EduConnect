package com.wecp.progressive.dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.wecp.progressive.config.DatabaseConnectionManager;
import com.wecp.progressive.entity.Course;

public class CourseDAOImpl implements CourseDAO {
    Connection connection;

    public CourseDAOImpl() {
        try
        {
            connection=DatabaseConnectionManager.getConnection() ;
        }
        catch (SQLException e)
        {
            System.out.println(e.getMessage());
        }
        
    }

    @Override
    public int addCourse(Course course) throws SQLException {
        String query="insert into course(course_name, description, teacher_id) values(?,?,?)";
        try(PreparedStatement ps=connection.prepareStatement(query,Statement.RETURN_GENERATED_KEYS);)
        {
            ps.setString(1, course.getCourseName());
            ps.setString(2,course.getDescription());
           // ps.setInt(3,course.getTeacherId());
            
            int affectedRows = ps.executeUpdate();

            if(affectedRows>0)
            {
                try(ResultSet rs=ps.getGeneratedKeys())
                {
                    if(rs.next())
                    {
                        course.setCourseId(rs.getInt(1));
                        return course.getCourseId();
                    }
                }
            }
            
        }
        catch(SQLException e)
        {
            throw new SQLException(e.getMessage());
        }
        return -1;
    }

    @Override
    public void deleteCourse(int courseId) throws SQLException {
        String query="delete from course where course_id=?";
        try(PreparedStatement ps=connection.prepareStatement(query);)
        {
            ps.setInt(1, courseId);   
            ps.executeUpdate();
        }
        catch(SQLException e)
        {
            throw new SQLException(e.getMessage());
        }
        
    }

    @Override
    public List<Course> getAllCourses() throws SQLException {
        List<Course> li= new ArrayList<>();
        
        String query="select * from course";
        try(Statement ps=connection.createStatement())
        {
            try(ResultSet rs=ps.executeQuery(query);)
            {
                while(rs.next())
                {
                    //li.add(new Course(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getInt(4)));
                }
            }
        }
        catch(SQLException e)
        {
            throw new SQLException(e.getMessage());
        }
        return li;
        
    }

    @Override
    public Course getCourseById(int courseId) throws SQLException {
        String query="select * from course where course_id=?";
        try(PreparedStatement ps=connection.prepareStatement(query);)
        {
            ps.setInt(1, courseId);
            try(ResultSet rs=ps.executeQuery();)
            {
                if(rs.next())
                {
                    //return new Course(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getInt(4));
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
    public void updateCourse(Course course) throws SQLException {
        String query="update course set course_name=?, description=?, teacher_id=? where course_id=?";
        try(PreparedStatement ps=connection.prepareStatement(query);)
        {
            ps.setString(1, course.getCourseName());
            ps.setString(2, course.getDescription());
           // ps.setInt(3, course.getTeacherId());
            ps.setInt(4, course.getCourseId());
            ps.executeUpdate();
        }
        catch(SQLException e)
        {
            throw new SQLException(e.getMessage());
        }
        
    }
    
}
