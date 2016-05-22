/*
;;; # CMUdict  --  Major Version: 0.07
        ;;;
        ;;; # $HeadURL$
        ;;; # $Date::                                                   $:
        ;;; # $Id::                                                     $:
        ;;; # $Rev::                                                    $:
        ;;; # $Author::                                                 $:
        ;;;
        ;;; #
        ;;; # ========================================================================
        ;;; # Copyright (C) 1993-2015 Carnegie Mellon University. All rights reserved.
        ;;; #
        ;;; # Redistribution and use in source and binary forms, with or without
        ;;; # modification, are permitted provided that the following conditions
        ;;; # are met:
        ;;; #
        ;;; # 1. Redistributions of source code must retain the above copyright
        ;;; #    notice, this list of conditions and the following disclaimer.
        ;;; #    The contents of this file are deemed to be source code.
        ;;; #
        ;;; # 2. Redistributions in binary form must reproduce the above copyright
        ;;; #    notice, this list of conditions and the following disclaimer in
        ;;; #    the documentation and/or other materials provided with the
        ;;; #    distribution.
        ;;; #
        ;;; # This work was supported in part by funding from the Defense Advanced
        ;;; # Research Projects Agency, the Office of Naval Research and the National
        ;;; # Science Foundation of the United States of America, and by member
        ;;; # companies of the Carnegie Mellon Sphinx Speech Consortium. We acknowledge
        ;;; # the contributions of many volunteers to the expansion and improvement of
        ;;; # this dictionary.
        ;;; #
        ;;; # THIS SOFTWARE IS PROVIDED BY CARNEGIE MELLON UNIVERSITY ``AS IS'' AND
        ;;; # ANY EXPRESSED OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
        ;;; # THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
        ;;; # PURPOSE ARE DISCLAIMED.  IN NO EVENT SHALL CARNEGIE MELLON UNIVERSITY
        ;;; # NOR ITS EMPLOYEES BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
        ;;; # SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT
        ;;; # LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,
        ;;; # DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY
        ;;; # THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
        ;;; # (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
        ;;; # OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
        ;;; #
        ;;; # ========================================================================
        ;;; #
        ;;;
        ;;;  NOTES  ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
        ;;;
        ;;;  [20080401] (air)  New dict file format introduced
        ;;;   - comments (like this section) are allowed
        ;;;   - file name is major version; vers/rev information is now in the header
        ;;;
        ;;;
        ;;; ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
        ;;;
*/

package net.magnusfrater;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TextToSpeech extends JFrame {

    //FRAME
    Dimension size = new Dimension(350,300);

    Speak s;
    DatabaseFun df;

    //PANELS
    //top
    JTextField jtfInput;
    //bot
    JButton jbSpeak;

    int x = 0;

    public TextToSpeech(){
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(size);
        setResizable(false);
        setLocationRelativeTo(null);
        setTitle("Text To Speech: by Todd Griffin");

        initComponents();

        s = new Speak();
        df = new DatabaseFun();

        setVisible(true);
    }

    private void initComponents(){
        //panels
        JPanel mid = new JPanel(new BorderLayout());
        JPanel bot = new JPanel();

        //top
        jtfInput = new JTextField();
        mid.add(jtfInput);

        mid.setBorder(BorderFactory.createEmptyBorder(10,10,0,10));

        //bot
        jbSpeak = new JButton("Speak");
        jbSpeak.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                new Thread(new Runnable(){
                    public void run(){
                        s.speak(jtfInput.getText());
                    }
                }).start();
            }
        });
        bot.add(jbSpeak);

        //frame
        add(mid,BorderLayout.CENTER);
        add(bot,BorderLayout.SOUTH);
    }

    public static void main(String[] args){
        TextToSpeech tts = new TextToSpeech();

        tts.df.printArpasoundWords("AO0",false,false);
    }
}