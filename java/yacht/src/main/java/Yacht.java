import java.util.HashSet;
import java.util.Set;

class Yacht {

    private int[] dice;
    private YachtCategory yachtCategory;

    Yacht(int[] dice, YachtCategory yachtCategory) {
        this.dice = dice;
        this.yachtCategory = yachtCategory;
    }

    public int score() {
        switch (yachtCategory) {
            case ONES: return sumOfDice(1);
            case TWOS: return sumOfDice(2);
            case THREES: return sumOfDice(3);
            case FOURS: return sumOfDice(4);
            case FIVES: return sumOfDice(5);
            case SIXES: return sumOfDice(6);
            case FULL_HOUSE: if(fullHouseOrFourOfAKind() == 0){return sumAll();} else {return 0;}
            case FOUR_OF_A_KIND: int output = fullHouseOrFourOfAKind(); if(output > 0){return output;} else {return 0;}
            case LITTLE_STRAIGHT:  if(straight() == "little"){return 30;} else {return 0;}
            case BIG_STRAIGHT: if(straight() == "big"){return 30;} else {return 0;}
            case CHOICE: return sumAll();
            case YACHT: if(yacht()){return 50;} else {return 0;}
        }
        return 0;
    }

    private int sumAll() {
        int score = 0;
        for (int die : this.dice){
            score += die;
        }
        return score;
    }

    private int sumOfDice(int i) {
        int score =0;
        for(int die : this.dice){
            if(die == i){
                score += die;
            }
        }
        return score;
    }

    private int fullHouseOrFourOfAKind() {

        int[] frequencyArray = {0,0,0,0,0,0};
        int uniqueNumbers = 0;
        Set<Integer> setOfNumbers = new HashSet<>();

        for (int die : dice) {
            if (setOfNumbers.contains(die)) {
                frequencyArray[die-1]++;
            } else {
                setOfNumbers.add(die);
                frequencyArray[die-1]++;
                uniqueNumbers++;
            }
        }

        if (uniqueNumbers > 2) {
            return -1;
        }

        for (int i = 0; i < 6; i++) {
            if (frequencyArray[i] == 3) {
                // Full House
                return 0;
            } else if (frequencyArray[i] >= 4) {
                // Four Of a Kind
                return 4 * (i+1);
            }
        }

        // Shouldn't be reached
        return -1;
    }

    private String straight() {
        Set<Integer> setOfNumbers = new HashSet<>();
        int sum=0;

        for (int die : this.dice){
            sum += die;
            setOfNumbers.add(die);
        }

        if(setOfNumbers.size()==5 && sum==15) {
            return "little";
        } else if(setOfNumbers.size() == 5 && sum==20) {
            return "big";
        } else {return "N/A";}

    }

    private boolean yacht() {
        for(int i=2; i < 5; i++){
            if(this.dice[0] == this.dice[i]){
                continue;
            } else { return false;}
        }
        return true;
    }

}
