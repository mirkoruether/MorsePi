package com.net.main;

import java.io.IOException;
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

        alreadyFound.put(' ', " /");

        text = "";
        for(char c : chars)
            if(alreadyFound.containsKey(c))
                text += alreadyFound.get(c) + " ";
            else
            {
                foundCode = "";
                findChar(tree, "", c);
                text += foundCode + " ";
                alreadyFound.put(c, foundCode);
            }
        while(text.endsWith(" ") || text.endsWith("/"))
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
        new ConsoleMain(args).run(args);
    }

    public ConsoleMain(String[] args)
    {
        try
        {
            this.tree = initTree();
            int pin;
            if(args.length < 1)
            {
                System.out.print("Nummer des Pins:");
                pin = Integer.parseInt(in(2));
            }
            else
                pin = Integer.parseInt(args[0]);
            output = new Output(pin);
        } catch(Exception ex)
        {
            ex.printStackTrace();
        }
    }

    public void run(String[] args)
    {
        try
        {
            String toMorse = "";
            if(args.length < 2)
            {
                System.out.println("Nachricht:");
                toMorse = in(256);
            }
            else
                for(int i = 1; i < args.length; i++)
                {
                    if(i > 1)
                        toMorse += " ";
                    toMorse += args[i];
                }

            System.out.println(toMorse);
            System.out.println(encode(toMorse));

            output.morse(encode(toMorse));

        } catch(Exception ex)
        {
            ex.printStackTrace();
        }
    }

    private String in(int length) throws IOException
    {
        byte[] b = new byte[length];
        System.in.read(b);
        String s = new String(b);
        while(s.endsWith(" ") || s.endsWith("\n"))
            s = s.substring(0, s.length() - 1);
        return s;
    }
}
