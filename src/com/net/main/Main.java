package com.net.main;

import java.util.HashMap;

public class Main extends javax.swing.JFrame
{
    private static final long serialVersionUID = 1L;
    private Morsebaum tree;
    private String foundCode;
    private Output output;

    public Main()
    {
        tree = initTree();
        initComponents();
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents()
    {

        toMorseTxt = new javax.swing.JTextField();
        morseBtn = new javax.swing.JButton();
        progressBar = new javax.swing.JProgressBar();
        jLabel1 = new javax.swing.JLabel();
        pinNumberTxt = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        morseBtn.setText("Morse");
        morseBtn.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                morseBtnActionPerformed(evt);
            }
        });

        jLabel1.setText("Pin:");

        pinNumberTxt.setText("22");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(toMorseTxt)
                                .addComponent(progressBar, javax.swing.GroupLayout.DEFAULT_SIZE, 230, Short.MAX_VALUE)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                          .addComponent(jLabel1)
                                          .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                          .addComponent(pinNumberTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                          .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                          .addComponent(morseBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addContainerGap())
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(toMorseTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(morseBtn)
                                .addComponent(jLabel1)
                                .addComponent(pinNumberTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(progressBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void morseBtnActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_morseBtnActionPerformed
    {//GEN-HEADEREND:event_morseBtnActionPerformed
        try
        {
            String toSend = encode(toMorseTxt.getText());

            System.out.println(toSend);

            output = new Output(pinNumberTxt.getText());
            output.morse(toSend);

        } catch(Exception ex)
        {
        }
    }//GEN-LAST:event_morseBtnActionPerformed

    public static void main(String args[])
    {
        java.awt.EventQueue.invokeLater(new Runnable()
        {
            @Override
            public void run()
            {
                new Main().setVisible(true);
            }
        });
    }

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

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JButton morseBtn;
    private javax.swing.JTextField pinNumberTxt;
    private javax.swing.JProgressBar progressBar;
    private javax.swing.JTextField toMorseTxt;
    // End of variables declaration//GEN-END:variables
}
