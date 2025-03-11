package com.main.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

@Service
public class BankingService {
    private static final Logger logger = LogManager.getLogger(BankingService.class);

    public void processTransaction() {
        logger.info("Transaction processing started...");
        try {
            // Some logic
            logger.debug("Debugging transaction details...");
        } catch (Exception e) {
            logger.error("Error occurred while processing transaction", e);
        }
    }
}

