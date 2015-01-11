package com.net.main;

/**
 *
 * @author Markus
 */
public class Morsebaum
{

    private Morsebaum leftTree = null;
    private Morsebaum rightTree = null;
    private char content;

    public Morsebaum(char content)
    {
        this.content = content;
    }

    public Morsebaum(char content, Morsebaum leftTree, Morsebaum rightTree)
    {
        this.leftTree = leftTree;
        this.rightTree = rightTree;
        this.content = content;
    }

    public Morsebaum left()
    {
        return leftTree;
    }

    public Morsebaum right()
    {
        return rightTree;
    }

    public char getContent()
    {
        return content;
    }

    public void setLeft(Morsebaum pBaum)
    {
        leftTree = pBaum;
    }

    public void setRight(Morsebaum pBaum)
    {
        rightTree = pBaum;
    }
}
