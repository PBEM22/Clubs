package types;

public enum Gender {
    MALE("남성"),
    FEMALE("여성");

    private final String gender;

    Gender(String gender) {
        this.gender = gender;
    }

    public String getGender() {
        return gender;
    }

    public static Gender fromString(String memberGender){

        for (Gender gender : Gender.values()){
            if (gender.getGender().equals(memberGender)){
                return gender;
            }
        }
        throw new IllegalArgumentException("성별을 잘못입력하셨습니다: " + memberGender);
    }
}
