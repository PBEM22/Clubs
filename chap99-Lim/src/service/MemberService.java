package service;

import domain.Member;
import dto.LoginDTO;
import dto.MemberDTO;
import exception.CustomException;
import repository.MemberRepository;
import types.Clubs;

import java.io.IOException;
import java.util.List;

public class MemberService {

    private final MemberRepository memberRepository = new MemberRepository();

//    public MemberService(MemberRepository memberRepository) {
//        this.memberRepository = memberRepository;
//    }

    public void findAllMember(){
        List<MemberDTO> memberDTOS = memberRepository.selectAllMember();

        int count = 1;

        for (MemberDTO memberDTO : memberDTOS){
            System.out.println(count + ". " + memberDTO.getUserId());
            count++;
        }

    }

    // id로 사용자 찾기
    public void findMemberByNo(long id){

        MemberDTO selectMember = memberRepository.selectMember(id);

        if (selectMember != null){
            System.out.println(selectMember.toString());
        } else {
            System.out.println("해당 번호를 가진 사용자는 없습니다. id: " + id);
        }
    }

    // userId로 사용자 찾기
    public void findMemberByUserId(String userId){
        MemberDTO memberByUserId = memberRepository.findMemberByUserId(userId);

        if (memberByUserId != null){
            System.out.println(memberByUserId.toString());
        } else {
            System.out.println("해당 아이디는 존재하지 않는 아이디입니다. userId: " + userId);
        }
    }

    // 회원가입
    public void registerMember(Member member) throws CustomException {

        if (memberRepository.checkUserId(member.getUserId()) == 1){
            long lastId = memberRepository.selectLastMemberNo();
            member.setId(lastId + 1);

            int result = memberRepository.insertMember(member);

            if (result == 1) System.out.println(member.getUserId() + "님 회원 가입이 완료되었습니다.");
        }



    }

    // 사용자 삭제
    public void removeMember(long id){
        int result = memberRepository.deleteMember(id);

        if (result == 1){
            System.out.println("탈퇴가 완료 되었습니다.");
        } else {
            System.out.println("입력하신 번호에 해당하는 사용자가 없습니다.");
        }
    }

    // 사용자 정보 수정
    public void modifyMember(MemberDTO member) throws CustomException {

        if (memberRepository.checkUserId(member.getUserId()) == 1){
            int result = memberRepository.updateMember(member);

            if (result == 1){
                System.out.println("정보 수정이 완료되었습니다.");
            }
        }
    }

    // 로그인
    public LoginDTO login(LoginDTO loginDTO) throws CustomException {

        System.out.println(loginDTO.getUserId());
            return memberRepository.userLogin(loginDTO);
    }

    // 동아리 인원 확인
    public int checkClubMembers(int clubNo){
        return memberRepository.checkClubMembers(clubNo);
    }

    // 동아리 가입
    public void registerClub(long id) throws CustomException {
        Clubs clubs = memberRepository.checkClub(id);
        if (!clubs.equals(Clubs.NONE)){
            throw new CustomException("동아리 탈퇴를 먼저 진행해주세요.", "400");
        }
        System.out.println("동아리 가입에 성공했습니다. 가입한 동아리: " + clubs);
    }

    // 동아리 탈퇴
    public void deleteClub(long id){
        if (memberRepository.deleteClub(id) == 1){
            System.out.println("동아리 탈퇴에 성공했습니다.");
        } else {
            System.out.println("동아리가 존재하지 않습니다.");
        }
    }
}
