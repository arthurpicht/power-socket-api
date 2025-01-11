package de.arthurpicht.powerSocketApi.dummy;

import java.util.ArrayList;
import java.util.List;

public class BooleanList {

    private List<Boolean> booleanList;

    public BooleanList() {
        this.booleanList = new ArrayList<>();
    }

    public BooleanList(List<String> booleanListAsStrings) {
        List<Boolean> booleanList = new ArrayList<>();
        for (String string : booleanListAsStrings) {
            booleanList.add(Boolean.valueOf(string));
        }
        this.booleanList = reduce(booleanList);
    }

    public void set(int index, boolean value) {
        if (index < this.booleanList.size()) {
            this.booleanList.set(index, value);
            this.booleanList = reduce(booleanList);
        } else if (value) {
            this.booleanList = expandWithLastElementAsTrue(this.booleanList, index);
        }
    }

    public int getReducedSize() {
        return this.booleanList.size();
    }

    public boolean get(int index) {
        if (index < this.booleanList.size()) {
            return this.booleanList.get(index);
        }
        return false;
    }

    public List<String> asListOfStrings() {
        return toStringList(this.booleanList);
    }

    public static List<Boolean> reduce(List<Boolean> booleanList) {
        if (booleanList.isEmpty()) new ArrayList<>();
        int lastIndexOfTrue = booleanList.lastIndexOf(Boolean.TRUE);
        return booleanList.subList(0, lastIndexOfTrue + 1);
    }

    public static List<Boolean> expandWithLastElementAsTrue(List<Boolean> booleanList, int includedIndex) {
        if (booleanList.size() > includedIndex) return booleanList;
        List<Boolean> newBooleanList = new ArrayList<>(booleanList);
        int elementsToAdd = includedIndex - booleanList.size() - 1;
        for (int i = 0; i <= elementsToAdd; i++) {
            newBooleanList.add(Boolean.FALSE);
        }
        newBooleanList.add(Boolean.TRUE);
        return newBooleanList;
    }

    private List<String> toStringList(List<Boolean> booleanList) {
        List<String> stringList = new ArrayList<>();
        for (Boolean b : booleanList) {
            stringList.add(b.toString());
        }
        return stringList;
    }

}
