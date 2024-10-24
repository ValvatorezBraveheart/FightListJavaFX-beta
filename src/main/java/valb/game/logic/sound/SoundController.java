package valb.game.logic.sound;


import javafx.scene.media.MediaException;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.Media;
import javafx.util.Duration;

import java.util.Objects;

// Simplify playing sound
public class SoundController{
    private final static String soundFolder = "/valb/game/sounds/";

    static Media incorrectSFX;
    static Media correctSFX;
    static Media buttonSFX;
    static Media currentBGM;

    static MediaPlayer incorrectSFXMediaPlayer;
    static MediaPlayer correctSFXMediaPLayer;
    static MediaPlayer buttonSFXMediaPlayer;
    static MediaPlayer bgmMediaPlayer;

    static boolean hasAudio = true;
    public SoundController(){
        double startTime = System.currentTimeMillis();
        try {
            setUpSFX();
            setUpBGM();
        } catch (MediaException e){
            hasAudio = false;
            System.out.println("Cannot find path to sound");
        }
        if (hasAudio){
            this.setUpVolume();
            this.setUpReset();
            // Set so it can be replayed
            // For now just do looping
            bgmMediaPlayer.setOnEndOfMedia(() -> {
                bgmMediaPlayer.seek(Duration.ZERO); // Seek back to the start
                bgmMediaPlayer.play(); // Play again
            });
            // PLay the looping bgm
            bgmMediaPlayer.play();
        }
        double endTime = System.currentTimeMillis();
        System.out.println("Set sound takes" + (endTime-startTime)/1000);


    }

    private MediaPlayer setMediaToPlayer(String fileName) throws MediaException{
        return new MediaPlayer(new Media(Objects.requireNonNull(getClass().getResource(soundFolder + fileName)).toString()));
    }

    // Initial set up for SFX (will be empty if public)
    private void setUpSFX() throws MediaException{
        System.out.println("Setting up sfx");
        incorrectSFXMediaPlayer = this.setMediaToPlayer("incorrect_sfx.mp3");
        correctSFXMediaPLayer = this.setMediaToPlayer("correct_sfx.mp3");
        buttonSFXMediaPlayer = this.setMediaToPlayer("button_sfx.mp3");
    }

    private void setUpReset(){
        System.out.println("Setting up reset");
        setSFXReset(correctSFXMediaPLayer);
        setSFXReset(incorrectSFXMediaPlayer);
        setSFXReset(buttonSFXMediaPlayer);
    }
    // Shouldn't be used outside of setting up, no need for null check
    private void setSFXReset(MediaPlayer mediaPlayer) {
        assert mediaPlayer != null;
        mediaPlayer.setOnEndOfMedia(() -> {
            mediaPlayer.stop();
            mediaPlayer.seek(mediaPlayer.getStartTime());});
        System.out.println("Setting up reset");
    }

    private void setUpVolume(){
        System.out.println("Setting up volume");
        setVolume(correctSFXMediaPLayer,0.8);
        setVolume(incorrectSFXMediaPlayer,0.8);
        setVolume(buttonSFXMediaPlayer,0.8);
        setVolume(bgmMediaPlayer,0.2);
    }


    // Also used in setting
    public void setVolume(MediaPlayer mediaPlayer, double level){
        // Still check c ause they might change in setting
        if (mediaPlayer != null){
            mediaPlayer.setVolume(level);
        }
    }


    private void setUpBGM(){
        System.out.println("Setting up bgm");
        bgmMediaPlayer = this.setMediaToPlayer("background_music.mp3");
    }
    public void playCorrectSFX(){
        if (correctSFXMediaPLayer != null) {
            correctSFXMediaPLayer.play();
        }
    }
    public void playIncorrectSFX(){
        if (incorrectSFXMediaPlayer != null) {
            incorrectSFXMediaPlayer.play();
        }
    }
    public void playButtonSFX(){
        if (buttonSFXMediaPlayer != null) {
            buttonSFXMediaPlayer.play();
        }
    }


}
