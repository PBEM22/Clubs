import domain.Member;
import dto.LoginDTO;
import dto.MemberDTO;
import exception.CustomException;
import service.MemberService;
import types.Clubs;
import types.Gender;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Application {

    private static final MemberService memberService = new MemberService();

    public static void main(String[] args) throws InterruptedException {
        Scanner scanner = new Scanner(System.in);
        boolean isLogin = false;
        long memberId = 0;
        String user = " ";

        // 전체 반복문
        while (true){
            System.out.println("===============================");
            setTimeOut();
            System.out.println("===모아동에 오신 것을 환영합니다!===");
            setTimeOut();
            System.out.println("===============================");
            System.out.println("####MAIN####");
            setTimeOut();
            System.out.println("1. 사용자 관련 메뉴");
            setTimeOut();
            System.out.println("2. 동아리 관련 메뉴");
            setTimeOut();
            System.out.println("3. 모아동 종료하기");
            setTimeOut();
            System.out.print("메뉴 선택 : ");
            int choice = scanner.nextInt();
            boolean loop = false;
            switch (choice){

                // ===사용자 관련 반복문=== //
                case 1->{
                    loop = true;
                    while (loop){
                        setTimeOut();
                        System.out.println("####USER####");
                        setTimeOut();
                        System.out.println("1. 가입되어 있는 사용자 정보 출력");
                        System.out.println("2. 사용자 찾기");
                        System.out.println("3. 가입 하기");
                        System.out.println("4. 로그인");
                        System.out.println("5. 내 정보 보기");
                        System.out.println("6. 정보 수정");
                        System.out.println("7. 사용자 탈퇴");
                        System.out.println("8. 로그아웃");
                        System.out.println("9. id로 사용자 찾기");
                        System.out.println("99. 뒤로가기");

                        int secondChoice = scanner.nextInt();

                        switch (secondChoice){
                            case 1 -> memberService.findAllMember();
                            case 2 -> {
                                System.out.print("사용자 index 입력 : ");
                                try {
                                    int id = scanner.nextInt();
                                    memberService.findMemberByNo(id);

                                } catch (InputMismatchException e){
                                    System.out.println(e.getMessage());
                                    System.out.println("찾고자 하는 index값을 입력해 주세요.");
                                }

                            }
                            case 3 -> {
                                try {
                                    System.out.println("회원가입을 시작합니다.");

                                    setTimeOut();
                                    System.out.print("사용자 ID: ");
                                    String userId = scanner.next();

                                    setTimeOut();
                                    System.out.print("사용자 비밀번호: ");
                                    String userPw = scanner.next();

                                    setTimeOut();
                                    System.out.print("사용자 이름: ");
                                    String userName = scanner.next();

                                    setTimeOut();
                                    System.out.print("사용자 핸드폰번호 (010-xxxx-xxxx): ");
                                    String userPhone = scanner.next();

                                    setTimeOut();
                                    System.out.print("사용자 성별 (MALE/FEMALE): ");
                                    Gender gender = Gender.valueOf(scanner.next().toUpperCase());

                                    setTimeOut();
                                    System.out.print("사용자 학번 (8자리): ");
                                    String deptNo = scanner.next();

                                    setTimeOut();
                                    System.out.print("사용자 학년 (1~5): ");
                                    String grade = scanner.next();

                                    setTimeOut();
                                    System.out.print("학과: ");
                                    String dept = scanner.next();

                                    setTimeOut();
                                    System.out.print("동아리 선택 (VOLLEYBALL/BASKETBALL/SOCCER/COMPUTER/BAND) 중에 기입: ");
                                    Clubs club = Clubs.valueOf(scanner.next().toUpperCase());

                                    // Member 객체 생성
                                    Member newMember = new Member(0, userId, userPw, userName, userPhone, gender, deptNo, grade, dept, club);

                                    // MemberService를 통해 회원가입 처리
                                    memberService.registerMember(newMember);
                                } catch (CustomException e){
                                    System.out.println(e.getMessage() + ", 에러코드: " + e.getErrorCode());
                                }
                            }
                            case 4 -> {

                                if (isLogin){
                                    System.out.println("로그인 상태입니다. userId: " + user);
                                } else {
                                    try {
                                        System.out.print("아이디를 입력해 주세요: ");
                                        String loginId = scanner.next();

                                        System.out.print("비밀번호를 입력해 주세요: ");
                                        String loginPw = scanner.next();

                                        LoginDTO loginDTO = new LoginDTO(loginId, loginPw);

                                        LoginDTO login = memberService.login(loginDTO);
                                        isLogin = true;

                                        user = login.getUserId();
                                        memberId = login.getId();

                                    } catch (CustomException e){
                                        System.out.println(e.getMessage() + ", 에러코드: " + e.getErrorCode());
                                    }
                                }
                            }
                            case 5 -> {
                                // 내정보보기
                                if (isLogin){
                                    memberService.findMemberByNo(memberId);
                                } else {
                                    notLogin();
                                }
                            }
                            case 6 ->{
                                // 내 정보수정
                                if (isLogin){
                                    try {

                                        System.out.println("===기존 정보 출력===");
                                        setTimeOut();
                                        memberService.findMemberByNo(memberId);
                                        setTimeOut();
                                        System.out.println("==================");
                                        setTimeOut();

                                        System.out.println("###정보 수정을 시작합니다.###");

                                        System.out.print("수정할 사용자 ID: ");
                                        String userId = scanner.next();

                                        System.out.print("수정할 사용자 이름: ");
                                        String userName = scanner.next();

                                        System.out.print("수정할 사용자 핸드폰번호 (010-xxxx-xxxx): ");
                                        String userPhone = scanner.next();

                                        System.out.print("수정할 사용자 성별 (MALE/FEMALE): ");
                                        Gender gender = Gender.valueOf(scanner.next().toUpperCase());

                                        System.out.print("수정할 사용자 학번 (8자리): ");
                                        String deptNo = scanner.next();

                                        System.out.print("수정할 사용자 학년 (1~5): ");
                                        String grade = scanner.next();

                                        System.out.print("수정할 학과: ");
                                        String dept = scanner.next();

                                        System.out.print("수정할 동아리 선택 (VOLLEYBALL/BASKETBALL/SOCCER/COMPUTER/BAND) 중에 기입, 없다면 NONE 기입: ");
                                        Clubs club = Clubs.valueOf(scanner.next().toUpperCase());

                                        MemberDTO memberDTO = new MemberDTO(memberId, userId, userName,
                                                userPhone, gender, deptNo, grade, dept, club);

                                        memberService.modifyMember(memberDTO);
                                    } catch (CustomException e){
                                        System.out.println(e.getMessage() +", 에러코드: " + e.getMessage());
                                    }

                                } else {
                                    notLogin();
                                }
                            }
                            case 7 -> {
                                // 탈퇴하기
                                if (!isLogin){
                                    notLogin();
                                } else {
                                    memberService.removeMember(memberId);
                                }
                            }
                            case 8->{
                                // 로그아웃
                                if (!isLogin){
                                    notLogin();
                                } else {
                                    isLogin = false;
                                    user = " ";
                                    memberId = 0;
                                    System.out.println("로그아웃 되었습니다.");
                                }
                            }
                            case 9->{
                                // userId기반 찾기
                                System.out.print("조회하고 싶은 사용자 ID를 입력해 주세요: ");
                                String next = scanner.next();

                                memberService.findMemberByUserId(next);
                            }

                            case 99 -> {
                                loop = false;
                            }

                            default -> printFault();

                        } // 사용자 메뉴
                    } // 사용자 관련 반복문 종료

                } // case 1 종료

                // ===동아리 관련 반복문=== //
                case 2->{
                    loop = true;

                    while (loop){
                        setTimeOut();
                        System.out.println("####CLUB####");
                        setTimeOut();
                        System.out.println("1. 동아리 리스트 보기");
                        setTimeOut();
                        System.out.println("2. 동아리 상세 보기");
                        System.out.println("3. 동아리 가입 하기 (로그인 필요)");
                        System.out.println("4. 동아리 탈퇴 하기 (로그인 필요)");
                        setTimeOut();
                        System.out.println("99. 뒤로가기");

                        int secondChoice = scanner.nextInt();

                        switch (secondChoice){
                            case 1 -> {
                                setTimeOut();
                                System.out.println("VOLLEYBALL 배구부");
                                System.out.println("BASKETBALL 농구부");
                                System.out.println("SOCCER 축구부");
                                System.out.println("COMPUTER 컴퓨터부");
                                System.out.println("BAND 밴드부");
                            }
                            case 2 -> {
                                setTimeOut();
                                System.out.println("!== 동아리 상세 보기 ==!");
                                System.out.println("1. VOLLEYBALL 배구부");
                                System.out.println("2. BASKETBALL 농구부");
                                System.out.println("3. SOCCER 축구부");
                                System.out.println("4. COMPUTER 컴퓨터부");
                                System.out.println("5. BAND 밴드부");

                                int option = scanner.nextInt();

                                switch (option){
                                    case 1 ->{
                                        setTimeOut();
                                        System.out.println("VOLLEYBALL 배구부");
                                        System.out.println("슬로건: 승리가 일상이 될 때까지..!");
                                        System.out.println("회장: 임성진");
                                        System.out.println("동아리 인원: " + memberService.checkClubMembers(option));
                                    }
                                    case 2->{
                                        setTimeOut();
                                        System.out.println("BASKETBALL 농구부");
                                        System.out.println("슬로건: 왼손은 거들뿐..");
                                        System.out.println("회장: 강백호");
                                        System.out.println("동아리 인원: " + memberService.checkClubMembers(option));

                                    }
                                    case 3->{
                                        setTimeOut();
                                        System.out.println("SOCCER 축구부");
                                        System.out.println("슬로건: 내게 축구는 살인이다.");
                                        System.out.println("회장: 손흥민");
                                        System.out.println("동아리 인원: " + memberService.checkClubMembers(option));

                                    }
                                    case 4->{
                                        setTimeOut();
                                        System.out.println("COMPUTER 컴퓨터부");
                                        System.out.println("슬로건: 00110101011010");
                                        System.out.println("회장: 김영한");
                                        System.out.println("동아리 인원: " + memberService.checkClubMembers(option));

                                    }
                                    case 5->{
                                        setTimeOut();
                                        System.out.println("BAND 밴드부");
                                        System.out.println("슬로건: 행복의 선율");
                                        System.out.println("회장: 고지식");
                                        System.out.println("동아리 인원: " + memberService.checkClubMembers(option));

                                    }
                                    default -> printFault();
                                }
                            } // 동아리 상세 스위치문 끝단
                            case 3 ->{
                                // 동아리 가입 (이미 동아리 존재시에는 탈퇴 먼저 하기)
                                if (!isLogin){
                                    notLogin();
                                } else {
                                    try {
                                        System.out.print("가입하고 싶은 동아리를 입력해주세요. (VOLLEYBALL/BASKETBALL/SOCCER/COMPUTER/BAND) 중에 기입: ");
                                        String club = scanner.next();
                                        memberService.registerClub(memberId);
                                    } catch (CustomException e){
                                        System.out.println(e.getMessage() + ", 에러코드: " + e.getErrorCode());
                                    }
                                }
                            }
                            case 4 ->{
                                // 동아리 탈퇴
                                if (!isLogin){
                                    notLogin();
                                }else {
                                    memberService.deleteClub(memberId);
                                }
                            }
                            case 99 -> {
                                loop = false;
                            }
                            default -> printFault();
                        } // 동아리 관련 스위치문 종료
                    } // 동아리 관련 반복문 종료
                } // choice 2 끝

                case 3->{
                    System.out.println("시스템을 종료합니다.");
                    System.exit(0);
                }
                default -> printFault();
            } // 전체 스위치문 종료

        }

    }

    // 잘못된 선택
    public static void printFault(){
        System.out.println("잘못된 선택입니다.");
    }

    // 로그인 유무
    public static void notLogin(){
        System.out.println("로그인 이후 사용 가능한 기능입니다.");
    }

    // 시간 설정
    public static void setTimeOut() throws InterruptedException {
        Thread.sleep(500);
    }
}
