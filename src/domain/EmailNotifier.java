package domain;

public class EmailNotifier implements UserObserver {
    @Override
    public void notify(String message) {
        System.out.println("[Notification] " + message);
    }
}

