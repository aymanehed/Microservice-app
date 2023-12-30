package client;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;
import ma.ace.stubs.Bank;
import ma.ace.stubs.BankServiceGrpc;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

public class GrpcClient2 {
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
        StreamObserver<Bank.ConvertCurrencyRequest> fullStream=asyncStub.fullCurrencyStream(new StreamObserver<Bank.ConvertCurrencyResponse>() {
            @Override
            public void onNext(Bank.ConvertCurrencyResponse convertCurrencyResponse) {
                System.out.println("----START-----");
                System.out.println(convertCurrencyResponse);
                System.out.println("---------");
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

        Timer timer=new Timer();//chaque seconde on pousse un objet
        timer.schedule(new TimerTask() {
            int counter=0;
            @Override
            public void run() {
                Bank.ConvertCurrencyRequest request = Bank.ConvertCurrencyRequest.newBuilder()
                        .setAmount(8600)
                        .setCurrencyFrom("MAD")
                        .setCurrencyTo("EUR")
                        .build();
                fullStream.onNext(request);
                System.out.println(""+counter);
                ++counter;
                if(counter==20)
                {
                    fullStream.onCompleted();
                    timer.cancel();
                }
            }
        }, 1000, 1000);
        System.out.println("?");
        System.in.read();
    }
}