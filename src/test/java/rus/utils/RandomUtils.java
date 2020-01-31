package rus.utils;

import java.util.Random;

public class RandomUtils {

        public int getRand() {
            return (int) (System.currentTimeMillis() % 100000);
        }

        public String getRandomAlphabetChar(){
            Random r = new Random();
            return String.valueOf((char)(r.nextInt(26)+'a'));
        }
        private String getRandomVowel(){
            char[] vowel = {'a','e','i','o','u'};
            Random r = new Random();
            return String.valueOf(vowel[r.nextInt(vowel.length)]);
        }
        private String getRandomConsonant(){
            char[] consonant = {'b','c','d','f','g','h','j','k','l', 'm','n','p','m','q','r','s','t','v','w','x','y','z'};
            Random r = new Random();
            return String.valueOf(consonant[r.nextInt(consonant.length)]);
        }
        public String getLoremIpsum(){
            return  getRandomConsonant()+
                    getRandomVowel()+
                    getRandomConsonant()+
                    getRandomVowel()+
                    getRandomConsonant()+
                    getRandomVowel()+
                    getRandomConsonant();
        }

}
