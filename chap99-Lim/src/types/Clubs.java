package types;

public enum Clubs {
    VOLLEYBALL("배구부"),
    BASKETBALL("농구부"),
    SOCCER("축구부"),
    COMPUTER("컴퓨터부"),
    BAND("밴드부"),
    NONE("없음");

    private final String clubs;

    Clubs(String clubs) {
        this.clubs = clubs;
    }

    public String getClubs() {
        return clubs;
    }

    public static Clubs fromString(String clubName){

        for (Clubs clubs : Clubs.values()){
            if (clubs.getClubs().equals(clubName)){
                return clubs;
            }
        }
        throw new IllegalArgumentException("해당 이름의 동아리가 존재하지 않습니다: " + clubName);
    }
}
