package client;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;
import ma.ace.stubs.Bank;
import ma.ace.stubs.BankServiceGrpc;
import java.io.IOException;

public class GrpcClient1 {
    public static void main(String[] args) throws IOException {
        ManagedChannel managedChannel = ManagedChannelBuilder.forAddress("localhost", 9998)
                .usePlaintext()
                .build();
        BankServiceGrpc.BankServiceStub asyncStub = BankServiceGrpc.newStub(managedChannel);
        Bank.ConvertCurrencyRequest request = Bank.ConvertCurrencyRequest.newBuilder()
                .setAmount(12000)
                .setCurrencyFrom("MAD")
                .setCurrencyTo("EUR")
                .build();
        asyncStub.convert(request, new StreamObserver<Bank.ConvertCurrencyResponse>() {
            @Override
            public void onNext(Bank.ConvertCurrencyResponse convertCurrencyResponse) {
                System.out.println("****START*****");
                System.out.println(convertCurrencyResponse);
            }

            @Override
            public void onError(Throwable throwable) {
                throwable.getMessage();
            }

            @Override
            public void onCompleted() {
                System.out.println("****EEEND*****");
            }
        });
        System.out.println("?");
        System.in.read();
    }
}