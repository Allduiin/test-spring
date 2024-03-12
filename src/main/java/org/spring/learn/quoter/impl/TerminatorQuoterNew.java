package org.spring.learn.quoter.impl;

import javax.annotation.PostConstruct;
import org.spring.learn.quoter.Quoter;

public class TerminatorQuoterNew implements Quoter {

    @Override
    @PostConstruct
    public void sayQuote() {
        System.out.println("Terminator quoter new impl");
    }
}
