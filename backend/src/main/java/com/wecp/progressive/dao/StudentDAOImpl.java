package com.wecp.progressive.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.wecp.progressive.config.DatabaseConnectionManager;
import com.wecp.progressive.entity.Student;

public class StudentDAOImpl implements StudentDAO {

    Connection connection;
    
    
    public StudentDAOImpl() {
        try{
            connection=DatabaseConnectionManager.getConnection();
        }
        catch(SQLException e)
        {
            System.out.println("Failed to establish a connection."+ e.getMessage());
        }
    }
    @Override
    public int addStudent(Student student) throws SQLException {
        String query="insert into student(full_name, date_of_birth, contact_number, email, address) values(?,?,?,?,?)";
        try(PreparedStatement ps=connection.prepareStatement(query,Statement.RETURN_GENERATED_KEYS);)
        {
            ps.setString(1, student.getFullName());
            ps.setDate(2, new java.sql.Date(student.getDateOfBirth().getTime()));
            ps.setString(3,student.getContactNumber());
            ps.setString(4, student.getEmail());
            ps.setString(5, student.getAddress());
            
            int affectedRows = ps.executeUpdate();

            if (affectedRows > 0)
            {
                try(ResultSet rs=ps.getGeneratedKeys())
                {
                    if(rs.next())
                    {
                        student.setStudentId(rs.getInt(1));
                        return student.getStudentId();
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
    public void deleteStudent(int studentId) throws SQLException {
        String query = "delete from student where student_id=?";
        try(PreparedStatement ps=connection.prepareStatement(query);)
        {
            ps.setInt(1, studentId);
            ps.executeUpdate();
        }   
        catch(SQLException e)
        {
            throw new SQLException(e.getMessage());
        }
    }

    @Override
    public List<Student> getAllStudents() throws SQLException {
        List<Student> li= new ArrayList<>();
        String query="select * from student";
        try(PreparedStatement ps=connection.prepareStatement(query);)
        {
            try(ResultSet rs=ps.executeQuery();)
            {
                while(rs.next())
                {
                    li.add(new Student(rs.getInt(1),rs.getString(2),rs.getDate(3),rs.getString(4),rs.getString(5),rs.getString(6)));
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
    public Student getStudentById(int studentId) throws SQLException {
        
        String query="select * from student where student_id=?";
        try(PreparedStatement ps=connection.prepareStatement(query);)
        {
            ps.setInt(1, studentId);
            try(ResultSet rs=ps.executeQuery();)
            {
                if(rs.next())
                {
                    return new Student(rs.getInt(1),rs.getString(2),rs.getDate(3),rs.getString(4),rs.getString(5),rs.getString(6));
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
    public void updateStudent(Student student) throws SQLException {
        String query="update student set full_name=?, date_of_birth=?, contact_number=?, email=?, address=? where student_id=?";
        try(PreparedStatement ps=connection.prepareStatement(query);)
        {
            ps.setString(1, student.getFullName());
            ps.setDate(2, new java.sql.Date(student.getDateOfBirth().getTime()));
            ps.setString(3, student.getContactNumber());
            ps.setString(4, student.getEmail());
            ps.setString(5, student.getAddress());
            ps.setInt(6, student.getStudentId());

            ps.executeUpdate();
        }
        catch(SQLException e)
        {
            throw new SQLException(e.getMessage());
        }
        
    }
    
}
