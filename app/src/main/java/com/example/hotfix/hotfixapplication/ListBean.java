package com.example.hotfix.hotfixapplication;


public class ListBean {

    public ListBean(String name, boolean isSelected, int index) {
        this.name = name;
        this.isSelected = isSelected;
        this.index = index;
    }

    public ListBean() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String name;

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    private boolean isSelected;
    private int index;

}
