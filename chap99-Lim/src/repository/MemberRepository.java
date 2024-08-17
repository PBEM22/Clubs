package repository;

import domain.Member;
import dto.LoginDTO;
import dto.MemberDTO;
import exception.CustomException;
import stream.MyObjectOutputStream;
import types.Clubs;
import types.Gender;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MemberRepository {

    private final ArrayList<Member> memberList = new ArrayList<>();
    private static final String FILE_PATH = "src/db/memberDB.dat";

    // 생성자, 정보가 없을 시에는 더미데이터 들어감
    public MemberRepository() {

        File file = new File(FILE_PATH);

        if (file.length() == 0){
            ArrayList<Member> members = new ArrayList<>();
            members.add(new Member(1, "user01", "password01", "John Doe", "010-1234-5678",
                    Gender.MALE, "20230001", "3", "Computer Science", Clubs.BAND));

            members.add(new Member(2, "user02", "password02", "Jane Doe", "010-2345-6789",
                    Gender.FEMALE, "20230002", "2", "Electrical Engineering", Clubs.COMPUTER));

            members.add(new Member(3, "user03", "password03", "Sam Smith", "010-3456-7890",
                    Gender.MALE, "20230003", "1", "Business Administration", Clubs.BASKETBALL));

            members.add(new Member(4, "user04", "password04", "Emily Johnson", "010-4567-8901",
                    Gender.FEMALE, "20230004", "4", "Mechanical Engineering", Clubs.VOLLEYBALL));

            members.add(new Member(5, "user05", "password05", "Michael Brown", "010-5678-9012",
                    Gender.MALE, "20230005", "5", "Civil Engineering", Clubs.VOLLEYBALL));

            saveMembers(file, members);
        }

        loadMembers(file);
    }

    // 사용자 불러오기
    private void loadMembers(File file){

        try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))){
            int i = 0;
            while (true){

                memberList.add((Member)ois.readObject());

            }

        } catch (EOFException e){
            System.out.println("전체 학생 정보 로딩 완료");
        } catch (IOException | ClassNotFoundException e){
            throw new RuntimeException(e);
        }

    }

    // 사용자 저장
    private void saveMembers(File file, ArrayList<Member> members){
        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))){

            for (Member member : members){
                oos.writeObject(member);
            }
        } catch (IOException e){
            throw new RuntimeException(e);
        }
    }

    // 모든 사용자 출력
    public List<MemberDTO> selectAllMember(){

        List<MemberDTO> memberDTOList = new ArrayList<>();

        for (Member member : memberList){
            MemberDTO memberDTO = new MemberDTO(
                    member.getId(),
                    member.getUserId(),
                    member.getUserName(),
                    member.getUserPhone(),
                    member.getGender(),
                    member.getDeptNo(),
                    member.getGrade(),
                    member.getDept(),
                    member.getClub()
            );

            memberDTOList.add(memberDTO);
        }

        return memberDTOList;
    }

    // 사용자 Id로 찾기
    public MemberDTO selectMember(long id){

        for (Member member : memberList){
            if (member.getId() == id){
                return new MemberDTO(
                        member.getId(),
                        member.getUserId(),
                        member.getUserName(),
                        member.getUserPhone(),
                        member.getGender(),
                        member.getDeptNo(),
                        member.getGrade(),
                        member.getDept(),
                        member.getClub()
                );
            }
        }

        return null;
    }

    // 사용자 userId로 찾기
    public MemberDTO findMemberByUserId(String userId){

        for (Member member : memberList){
            if (member.getUserId().equals(userId)){
                return new MemberDTO(
                        member.getId(),
                        member.getUserId(),
                        member.getUserName(),
                        member.getUserPhone(),
                        member.getGender(),
                        member.getDeptNo(),
                        member.getGrade(),
                        member.getDept(),
                        member.getClub()
                );
            }
        }

        return null;
    }

    // 마지막 id값 반환 (인덱스 늘리기용)
    public long selectLastMemberNo(){
        Member lastMember = memberList.get(memberList.size() - 1);

        return lastMember.getId();
    }

    // 사용자 추가
    public int insertMember(Member member){

        int result = 0;

        try(MyObjectOutputStream moos = new MyObjectOutputStream(new FileOutputStream(FILE_PATH, true))){

            moos.writeObject(member);
            memberList.add(member);
            result = 1;

        } catch (IOException e){
            throw new RuntimeException(e);
        }

        return result;
    }

    // 사용자 삭제
    public int deleteMember(long id){

        for (int i =0; i<memberList.size(); i++){
            if (memberList.get(i).getId() == id){
                memberList.remove(i);

                File file = new File(FILE_PATH);
                saveMembers(file, memberList);

                return 1;
            }
        }

        return 0;
    }

    // 사용자 수정
    public int updateMember(MemberDTO member){

        for (int i=0; i<memberList.size(); i++){
            if (memberList.get(i).getId() == member.getId()){
                Member updateMember = memberList.get(i);

                memberList.set(i, updateMember.updateMember(member));

                File file = new File(FILE_PATH);
                saveMembers(file, memberList);

                return 1;
            }
        }

        return 0;

    }

    // 사용자 로그인 검증 (로그인 성공 LoginDTO, 실패 Exception)
    public LoginDTO userLogin(LoginDTO loginDTO) throws CustomException {

        for (Member member : memberList) {
            if (member.getUserId().equals(loginDTO.getUserId())) {

                if (member.getUserPw().equals(loginDTO.getUserPw())) {
                    loginDTO.setId(member.getId());
                    return loginDTO;
                } else {
                    throw new CustomException("틀린 비밀번호 입니다.", "401");
                }
            }
        }
            throw new CustomException("아이디가 존재하지 않습니다.", "404");

    }

    // 동아리 인원 파악 (1배구부, 2농구부, 3축구부, 4컴퓨터, 5밴드부)
    public int checkClubMembers(int clubNo){

        int count =0;
        switch (clubNo){
            case 1 -> {
                for (Member member : memberList){
                    if (member.getClub().equals(Clubs.VOLLEYBALL)){
                        count++;
                    }
                }
            }
            case 2 ->{
                for (Member member : memberList){
                    if (member.getClub().equals(Clubs.BASKETBALL)){
                        count++;
                    }
                }
            }
            case 3 ->{
                for (Member member : memberList){
                    if (member.getClub().equals(Clubs.SOCCER)){
                        count++;
                    }
                }
            }
            case 4 ->{
                for (Member member : memberList){
                    if (member.getClub().equals(Clubs.COMPUTER)){
                        count++;
                    }
                }
            }
            case 5 ->{
                for (Member member : memberList){
                    if (member.getClub().equals(Clubs.BAND)){
                        count++;
                    }
                }
            }
            default -> {
                System.out.println("잘못 입력 하셨습니다.");
            }
        }

        return count;
    }

    // 아이디 중복확인 (중복되지 않을시에 1반환)
    public int checkUserId(String userId) throws CustomException {
        for(Member member : memberList){
            if (member.getUserId().equals(userId)){
                throw new CustomException("이미 존재하는 아이디입니다.", "409");
            }
        }
        return 1;
    }

    // 동아리 가입 여부 확인 (NONE이면 1, 아니면 0)
    public Clubs checkClub(long memberId) throws CustomException {
        for (Member member : memberList){
            if (member.getId() == memberId){

                if (member.getClub().equals(Clubs.NONE)){
                    return Clubs.NONE;
                }else {
                    return member.getClub();
                }

            }
        }
        throw new CustomException("존재하지 않는 사용자입니다.", "404");
    }

    // 동아리 탈퇴
    public int deleteClub(long memberId){
        for (int i =0; i < memberList.size(); i++){
            if (memberList.get(i).getId() == memberId){

                Member member = memberList.get(i);
                member.setClub(Clubs.NONE);

                memberList.set(i, member);

                File file = new File(FILE_PATH);
                saveMembers(file, memberList);

                return 1;
            }
        }
        return 0;
    }

}
