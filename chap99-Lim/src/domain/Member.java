package domain;

import dto.MemberDTO;
import types.Clubs;
import types.Gender;

import java.io.Serializable;

public class Member implements Serializable {
    private long id;

    // 사용자 아이디
    private String userId;

    // 사용자 비밀번호
    private String userPw;

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

    public Member(long id, String userId, String userPw,
                  String userName, String userPhone, Gender gender,
                  String deptNo, String grade, String dept, Clubs clubs) {
        this.id = id;
        this.userId = userId;
        this.userPw = userPw;
        this.userName = userName;
        this.userPhone = userPhone;
        this.gender = gender;
        this.deptNo = deptNo;
        this.grade = grade;
        this.dept = dept;
        this.club = clubs;
    }

    public Member() {
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

    public String getUserPw() {
        return userPw;
    }

    public void setUserPw(String userPw) {
        this.userPw = userPw;
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
        return "Member{" +
                "id=" + id +
                ", userId='" + userId + '\'' +
                ", userPw='" + userPw + '\'' +
                ", userName='" + userName + '\'' +
                ", userPhone='" + userPhone + '\'' +
                ", gender=" + gender +
                ", deptNo='" + deptNo + '\'' +
                ", grade='" + grade + '\'' +
                ", dept='" + dept + '\'' +
                ", club=" + club +
                '}';
    }

    public Member updateMember(MemberDTO memberDTO){
        this.userId = memberDTO.getUserId();
        this.userName = memberDTO.getUserName();
        this.userPhone = memberDTO.getUserPhone();
        this.gender = memberDTO.getGender();
        this.deptNo = memberDTO.getDeptNo();
        this.grade = memberDTO.getGrade();
        this.dept = memberDTO.getDept();
        this.club = memberDTO.getClub();

        return this;
    }
}
