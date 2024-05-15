/*
This class stores the information of a single call (source phone number, target phone number,
date call started, time call started, and duration). In terms of methods, this is a very data-oriented class,
and it should only provide functions to access the values of its private, read-only attributes
 */

public class Call {
    //private attributes

    private String sourcePhoneNumber;

    private String targetPhoneNumber;
    private String dateCallStarted;

    private String timeCallStarted;

    private String duration;

    //constructor
    public Call(String sourcePhoneNumber, String targetPhoneNumber, String dateCallStarted, String timeCallStarted, String duration) {

        this.sourcePhoneNumber = sourcePhoneNumber;
        this.targetPhoneNumber = targetPhoneNumber;
        this.dateCallStarted = dateCallStarted;
        this.timeCallStarted = timeCallStarted;
        this.duration = duration;

    }

    //getter methods to be accessed by other classes
    public String getSourcePhoneNumber() {
        return sourcePhoneNumber;
    }

    public String getTargetPhoneNumber() {
        return targetPhoneNumber;
    }

    public String getDateCallStarted() {
        return dateCallStarted;
    }

    public String getTimeCallStarted() {
        return timeCallStarted;
    }

    public String getDuration() {
        return duration;
    }

    public String toString() {
        return String.format("Source number: %s; Target number: %s; Date the Call Started: %s; Time the Call Started: %s; Duration of call: %s", sourcePhoneNumber, targetPhoneNumber, dateCallStarted, timeCallStarted, duration);
    }









}
