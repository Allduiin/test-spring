package org.example.quoter.impl;

import javax.annotation.PostConstruct;
import org.example.quoter.Quoter;

public class TerminatorQuoterNew implements Quoter {

    @Override
    @PostConstruct
    public void sayQuote() {
        System.out.println("Terminator quoter new impl");
    }
}
