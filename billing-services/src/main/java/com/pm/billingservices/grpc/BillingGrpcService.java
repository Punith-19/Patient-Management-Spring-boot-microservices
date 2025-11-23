package com.pm.billingservices.grpc;

import billing.BillingResponse;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import billing.BillingServiceGrpc.BillingServiceImplBase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@GrpcService
public class BillingGrpcService extends BillingServiceImplBase{
    private static final Logger log = LoggerFactory.getLogger(BillingGrpcService.class);

    @Override
    public void createBillingAccount(billing.BillingRequest billingRequest, StreamObserver<BillingResponse> responseObserver){
        log.info("createBillingAccount request recieved {}", billingRequest.toString());
        //bussiness logic

        BillingResponse response = BillingResponse.newBuilder().setAccountId("1234").setStatus("ACTIVE").build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

}
