import java.util.*;

public class OpticalCharacterReader {

    private Map<String, String> digitShape = new HashMap<>();

    public OpticalCharacterReader() {
        this.digitShape.put(" _ | ||_|   ", "0");
        this.digitShape.put("     |  |   ", "1");
        this.digitShape.put(" _  _||_    ", "2");
        this.digitShape.put(" _  _| _|   ", "3");
        this.digitShape.put("   |_|  |   ", "4");
        this.digitShape.put(" _ |_  _|   ", "5");
        this.digitShape.put(" _ |_ |_|   ", "6");
        this.digitShape.put(" _   |  |   ", "7");
        this.digitShape.put(" _ |_||_|   ", "8");
        this.digitShape.put(" _ |_| _|   ", "9");
    }

    public String parse(List<String> digitInput) {

        checkInput(digitInput);

        String output = "";
        String digit;

        int inputLength = digitInput.get(0).length();
        int inputHeight = digitInput.size() / 4;

        for (int block = 0; block < inputHeight; block++) {
            for (int i = 0; i < inputLength; i += 3) {

                digit = "";

                for (int row = 0; row < 4; row++) {
                    digit += digitInput.get(block * 4 + row).substring(i, i + 3);
                }

                if (this.digitShape.get(digit) == null) {
                    output += "?";
                } else {
                    output += this.digitShape.get(digit);
                }
            }
            output += ",";
        }

        return output.substring(0, output.length() - 1);
    }

    private void checkInput(List<String> input) throws IllegalArgumentException {
        if (input.size() % 4 != 0) {
            throw new IllegalArgumentException("Number of input rows must be a positive multiple of 4");
        }

        for (String line : input) {
            if (line.length() % 3 != 0) {
                throw new IllegalArgumentException("Number of input columns must be a positive multiple of 3");
            }
        }
    }

}
