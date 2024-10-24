package valb.game.logic.timer;

import javafx.application.Platform;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

// Round timer count down from 60 seconds
public class RoundTimer{
    private int remainingTime;
    private final Timer timer = new Timer();
    private static final ArrayList<RoundTimerObserver> observers = new ArrayList<>();
    private class RoundTimerTask extends TimerTask{
        @Override
        public void run() {
            if (remainingTime > 0){
                remainingTime--;
                Platform.runLater(this::notifyRoundTimerCountDown);
            } else {
                Platform.runLater(this::notifyRoundTimerFinish);
                cancel(); // Stop the TimerTask
            }
        }
        private void notifyRoundTimerCountDown(){
            synchronized(observers){
                for (RoundTimerObserver observer:observers){
                    observer.roundTimerCountDown();
                }
            }
        }
        private void notifyRoundTimerFinish(){
            synchronized(observers){
                for (RoundTimerObserver observer:observers){
                    observer.roundTimerFinish();
                }
            }
        }


    }
    public void start(){
        timer.scheduleAtFixedRate(new RoundTimerTask(),0,1000);
    }
    public RoundTimer(){
        this(60); //default 60 second
    }
    public RoundTimer(int time){
        this.remainingTime = time;
    }
    public void addObserver(RoundTimerObserver observer){
        observers.add(observer);
    }
    public void removeObserver(RoundTimerObserver observer){
        observers.remove(observer);
    }
    public int getRemainingTime(){
        return this.remainingTime;
    }

    public void skipTime(){
        this.remainingTime = 0;
    }

}
