package org.example.quoter.impl;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.example.quoter.Quoter;
import javax.annotation.PostConstruct;

public class TerminatorQuoter implements Quoter {
    private static final Logger logger = Logger.getLogger(TerminatorQuoter.class.getName());
    private static final String LOVES = "She loves you :)";
    private static final String NOT_LOVES = "She doesn't loves you :(";

    @InjectLikeOrNot(min = 5, max = 12)
    private boolean like;
    private String message;

    public TerminatorQuoter() {
        logger.log(Level.INFO, "TerminatorQuoter created by constructor\n{0}", this);
    }

    @PostConstruct
    public void init() {
        logger.log(Level.INFO, "TerminatorQuoter init method used\n{0}", this);
    }

    @Override
    public void sayQuote() {
        System.out.println("Message = " + message);
        System.out.println(like? LOVES : NOT_LOVES);
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setLike(boolean like) {
        this.like = like;
    }

    @Override
    public String toString() {
        return "TerminatorQuoter{" +
               "like=" + like +
               ", message='" + message + '\'' +
               '}';
    }
}

