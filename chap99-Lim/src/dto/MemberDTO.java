package dto;

import types.Clubs;
import types.Gender;

import java.io.Serializable;

public class MemberDTO implements Serializable {

    private long id;

    // 사용자 아이디
    private String userId;

    // 사용자이름
    private String userName;

    // 사용자 핸드폰번호 (010-xxxx-xxxx 형식으로)
    private String userPhone;

    // 사용자 성별 (MALE, FEMALE)
    private Gender gender;

    // 사용자 학번 (8자리까지)
    private String deptNo;

    // 사용자 학년 (최대 5)
    private String grade;

    // 학과 번호로 할까.. Enum으로 할까..
    private String dept;

    // 가입된 동아리
    private Clubs club;

    public MemberDTO(long id, String userId, String userName, String userPhone, Gender gender, String deptNo,
                     String grade, String dept, Clubs club) {
        this.id = id;
        this.userId = userId;
        this.userName = userName;
        this.userPhone = userPhone;
        this.gender = gender;
        this.deptNo = deptNo;
        this.grade = grade;
        this.dept = dept;
        this.club = club;
    }

    public MemberDTO() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public String getDeptNo() {
        return deptNo;
    }

    public void setDeptNo(String deptNo) {
        this.deptNo = deptNo;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getDept() {
        return dept;
    }

    public void setDept(String dept) {
        this.dept = dept;
    }

    public Clubs getClub() {
        return club;
    }

    public void setClub(Clubs club) {
        this.club = club;
    }

    @Override
    public String toString() {
        return "{ id=" + id +
                ", userId='" + userId + '\'' +
                ", userName='" + userName + '\'' +
                ", userPhone='" + userPhone + '\'' +
                ", gender=" + gender +
                ", deptNo='" + deptNo + '\'' +
                ", grade='" + grade + '\'' +
                ", dept='" + dept + '\'' +
                ", club=" + club +
                '}';
    }
}
