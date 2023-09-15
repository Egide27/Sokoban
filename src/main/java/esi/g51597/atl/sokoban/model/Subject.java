package esi.g51597.atl.sokoban.model;

/**
 * Interface for Observable
 *
 * @author Egide Kabanza
 */
public interface Subject {

    void registerObserver(Observer obs);

    void removeObserver(Observer obs);

}
