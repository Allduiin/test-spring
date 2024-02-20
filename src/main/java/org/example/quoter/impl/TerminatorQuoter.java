package org.example.quoter.impl;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import org.example.quoter.Quoter;
import org.example.quoter.impl.annotations.AfterProxy;
import org.example.quoter.impl.annotations.InjectLikeOrNot;
import org.example.quoter.impl.annotations.Profiling;
import org.springframework.stereotype.Component;

@Profiling
@Component
public class TerminatorQuoter implements Quoter {
    private static final Logger logger = Logger.getLogger(TerminatorQuoter.class.getName());
    private static final String LOVES = "She loves you :)";
    private static final String NOT_LOVES = "She doesn't love you :(";

    @InjectLikeOrNot(min = 5, max = 12)
    private boolean like;
    private String message = "I'll be back";

    public TerminatorQuoter() {
        logger.log(Level.INFO, "TerminatorQuoter created by constructor\n{0}", this);
    }

    @PostConstruct
    public void init() {
        logger.log(Level.INFO, "TerminatorQuoter init method used\n{0}", this);
    }

    @AfterProxy
    public void sayQuote() {
        System.out.println("Message = " + message);
        System.out.println(like? LOVES : NOT_LOVES);
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

