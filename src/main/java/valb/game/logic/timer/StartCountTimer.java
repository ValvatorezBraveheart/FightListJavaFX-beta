package valb.game.logic.timer;

import javafx.application.Platform;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

// Countdown timer before round start
public class StartCountTimer{
    private int remainingTime;
    private final Timer timer = new Timer();
    private final ArrayList<StartCountObserver> observers = new ArrayList<>();
    private class StartCountTask extends TimerTask{
        @Override
        public void run() {
            if (remainingTime > 1){
                remainingTime--;
                Platform.runLater(this::notifyObserversCountDown);
            } else {
                Platform.runLater(this::notifyObserversFinish);
                cancel(); // Stop the TimerTask
            }

        }
        private void notifyObserversCountDown(){
            synchronized(observers){
                for (StartCountObserver observer:observers){
                    observer.startTimerCountDown();
                }
            }
        }
        private void notifyObserversFinish(){
            synchronized(observers){
                for (StartCountObserver observer:observers){
                    observer.startTimerFinish();
                }
            }
        }

    }

    public int getRemainingTime(){
        return this.remainingTime;
    }
    public StartCountTimer(){
        this(4);
    }

    public StartCountTimer(int time){
        this.remainingTime = time;
    }
    public void start(){
        timer.scheduleAtFixedRate(new StartCountTask(),0,1000);
    }
    public void addObserver(StartCountObserver observer){
        synchronized (observers){
            observers.add(observer);
        }
    }
    public void removeObserver(StartCountObserver observer){
        synchronized (observers){
            observers.remove(observer);
        }
    }
}
