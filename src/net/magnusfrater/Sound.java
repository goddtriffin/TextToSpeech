package net.magnusfrater;

import java.io.File;
import java.io.IOException;
import java.io.SequenceInputStream;
import java.util.ArrayList;
import javax.sound.sampled.*;

public class Sound {

    private final int BUFFER_SIZE = 128000;
    private File soundFile;
    private AudioInputStream audioStream;
    private AudioFormat audioFormat;
    private SourceDataLine sourceLine;

    protected void concatWav(ArrayList<String> paths){
        try{

            AudioInputStream clip1 = null;
            for (String path : paths)
            {
                if(clip1 == null)
                {
                    clip1 = AudioSystem.getAudioInputStream(new File(path));
                    continue;
                }
                AudioInputStream clip2 = AudioSystem.getAudioInputStream(new File(path));
                AudioInputStream appendedFiles = new AudioInputStream(
                        new SequenceInputStream(clip1, clip2),
                        clip1.getFormat(),
                        clip1.getFrameLength() + clip2.getFrameLength());
                clip1 = appendedFiles;
            }
            AudioSystem.write(clip1, AudioFileFormat.Type.WAVE, new File("src/res/arpasounds/concatWav.wav"));

        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void playWav(String wav){

        String strFilename = wav;

        try {
            soundFile = new File(strFilename);
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }

        try {
            audioStream = AudioSystem.getAudioInputStream(soundFile);
        } catch (Exception e){
            e.printStackTrace();
            System.exit(1);
        }

        audioFormat = audioStream.getFormat();

        DataLine.Info info = new DataLine.Info(SourceDataLine.class, audioFormat);
        try {
            sourceLine = (SourceDataLine) AudioSystem.getLine(info);
            sourceLine.open(audioFormat);
        } catch (LineUnavailableException e) {
            e.printStackTrace();
            System.exit(1);
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }

        sourceLine.start();

        int nBytesRead = 0;
        byte[] abData = new byte[BUFFER_SIZE];
        while (nBytesRead != -1) {
            try {
                nBytesRead = audioStream.read(abData, 0, abData.length);
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (nBytesRead >= 0) {
                @SuppressWarnings("unused")
                int nBytesWritten = sourceLine.write(abData, 0, nBytesRead);
            }
        }

        sourceLine.drain();
        sourceLine.close();
    }
}
