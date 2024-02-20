package org.example.quoter.impl;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import org.example.quoter.Quoter;
import org.example.quoter.impl.annotations.AfterProxy;
import org.example.quoter.impl.annotations.DeprecatedClass;
import org.example.quoter.impl.annotations.InjectLikeOrNot;
import org.example.quoter.impl.annotations.Profiling;
import org.springframework.stereotype.Component;

@Profiling
@Component
@DeprecatedClass(actualImpl = TerminatorQuoterNew.class)
public class TerminatorQuoterOld implements Quoter {
    private static final Logger logger = Logger.getLogger(TerminatorQuoterOld.class.getName());
    private static final String LOVES = "She loves you :)";
    private static final String NOT_LOVES = "She doesn't love you :(";

    @InjectLikeOrNot(min = 5, max = 12)
    private boolean like;
    private final String message = "I'll be back";

    public TerminatorQuoterOld() {
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

    @Override
    public String toString() {
        return "TerminatorQuoter{" +
               "like=" + like +
               ", message='" + message + '\'' +
               '}';
    }
}

