package com.yearupunited.models.services;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class SoundPlayer {

    // plays a sound file once
    public static void play(String filePath) {
        try {
            File soundFile = new File(filePath);
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(soundFile);
            Clip clip = AudioSystem.getClip();
            clip.open(audioStream);
            clip.start();

            // wait for clip to finish before returning
            Thread.sleep(clip.getMicrosecondLength() / 1000);
            clip.close();

        } catch (UnsupportedAudioFileException e) {
            System.out.println("Unsupported audio format: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Could not find audio file: " + e.getMessage());
        } catch (LineUnavailableException e) {
            System.out.println("Audio line unavailable: " + e.getMessage());
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

}
