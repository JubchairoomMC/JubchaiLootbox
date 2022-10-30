package net.jubchairoom.jzmc.oxygen.library.random;

import org.jetbrains.annotations.Nullable;

import java.util.*;

public class JubchaiRandom {

    private String randomBox;
    private HashMap<String, Double> chances = new HashMap<>();

    public JubchaiRandom(String randomBox) {
        this.randomBox = randomBox;
    }

    public String getRandomBox() {
        return this.randomBox;
    }

    public void add(double chance, String name) {
        chances.put(name, chance);
    }
    public void add(double chance, String... name) {
        for (String s : name) {
            chances.put(s, chance);
        }
    }

    public String random() {
        return random(false, null);
    }

    public String random(boolean isShuffle, @Nullable List<String> excepts) {

        Random ran = new Random(System.currentTimeMillis());
        double random = ran.nextDouble();

        double start = 0;
        List<String> shuffleArr = new ArrayList<>(this.chances.keySet());

        if (excepts == null) excepts = new ArrayList<>();
        if (isShuffle) Collections.shuffle(shuffleArr);

        for (String keys : shuffleArr) {
            if (excepts.contains(keys)) continue;

            double percentageBorder = start + this.chances.get(keys);
            if (random < percentageBorder) return keys;

            start = percentageBorder;
        }
        return ""; // Salted
    }

}
