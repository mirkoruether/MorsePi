package com.net.main;

import java.util.HashMap;

public class ConsoleMain
{
    static Output output;
    private Morsebaum tree;
    private String foundCode;

    private static Morsebaum initTree()
    {

        Morsebaum baum = new Morsebaum(('*'),
                                       new Morsebaum(('E'),
                                                     new Morsebaum(('I'),
                                                                   new Morsebaum(
                                                                           ('S'),
                                                                           new Morsebaum(('H')),
                                                                           new Morsebaum(('V'))),
                                                                   new Morsebaum(
                                                                           ('U'),
                                                                           new Morsebaum(('F')),
                                                                           new Morsebaum(('Ü')))
                                                     ),
                                                     new Morsebaum(('A'),
                                                                   new Morsebaum(
                                                                           ('R'),
                                                                           new Morsebaum(('L')),
                                                                           new Morsebaum(('Ä'))),
                                                                   new Morsebaum(
                                                                           ('W'),
                                                                           new Morsebaum(('P')),
                                                                           new Morsebaum(('J')))
                                                     )
                                       ),
                                       new Morsebaum(('T'),
                                                     new Morsebaum(('N'),
                                                                   new Morsebaum(
                                                                           ('D'),
                                                                           new Morsebaum(('B')),
                                                                           new Morsebaum(('X'))),
                                                                   new Morsebaum(
                                                                           ('K'),
                                                                           new Morsebaum(('C')),
                                                                           new Morsebaum(('Y')))
                                                     ),
                                                     new Morsebaum(('M'),
                                                                   new Morsebaum(
                                                                           ('G'),
                                                                           new Morsebaum(('Q')),
                                                                           new Morsebaum(('Z'))),
                                                                   new Morsebaum(
                                                                           ('O'),
                                                                           new Morsebaum(('Ö')),
                                                                           new Morsebaum(('ß')))
                                                     )
                                       )
        );
        return baum;
    }

    public String encode(String text)
    {
        char[] chars = text.toCharArray();
        HashMap<Character, String> alreadyFound = new HashMap<>();

        alreadyFound.put(' ', " ");

        text = "";
        for(char c : chars)
            if(alreadyFound.containsKey(c))
                text += alreadyFound.get(c) + "/";
            else
            {
                foundCode = "";
                findChar(tree, "", c);
                text += foundCode + "/";
                alreadyFound.put(c, foundCode);
            }
        while(text.endsWith("/"))
            text = text.substring(0, text.length() - 1);
        return text;
    }

    private void findChar(Morsebaum b, String code, char toFind)
    {
        if(b.getContent() == toFind)
            foundCode = code;
        else
        {
            if(b.left() != null)
                findChar(b.left(), code + ".", toFind);
            if(b.right() != null)
                findChar(b.right(), code + "-", toFind);
        }
    }

    public static void main(String[] args)
    {
        new ConsoleMain().run();
    }

    public ConsoleMain()
    {
        output = new Output("GPIO_" + "4");
    }

    public void run()
    {
        try
        {
            String toSend = encode("SOS");

            System.out.println(toSend);

            output.morse(toSend);

        } catch(Exception ex)
        {
            ex.printStackTrace();
        }
    }
}
