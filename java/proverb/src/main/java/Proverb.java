class Proverb {

    private String[] words;

    public Proverb(String[] words) {
        this.words = words;
    }

    String recite() {
        String proverbText = "";
        if (this.words.length == 0){
            return proverbText;
        }

        if(this.words.length > 1) {
            for (int index=0; index < words.length -1; index++){
                proverbText += "For want of a " + this.words[index] + " the " + this.words[index+1] + " was lost.\n";
            }
        }

        proverbText += "And all for the want of a " + this.words[0] + ".";

        System.out.println(proverbText);

        return  proverbText;
    }

}
