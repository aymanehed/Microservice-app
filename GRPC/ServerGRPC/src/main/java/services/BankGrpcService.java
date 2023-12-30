package services;
import io.grpc.stub.StreamObserver;
import ma.ace.stubs.Bank;
import ma.ace.stubs.BankServiceGrpc;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

public class BankGrpcService extends BankServiceGrpc.BankServiceImplBase {

    private static final Map<String, Double> currencies = new HashMap<>();

    static {
        currencies.put("USD", 1.0);
        currencies.put("EUR", 0.84);
        currencies.put("MAD", 1.4);
    }
    public void convert(Bank.ConvertCurrencyRequest request, StreamObserver<Bank.ConvertCurrencyResponse> responseObserver) {
        String currencyFrom=request.getCurrencyFrom();
        String currencyTo=request.getCurrencyTo();
        double amount=request.getAmount();
        double fromRate = currencies.get(currencyFrom);
        double toRate = currencies.get(currencyTo);
        double result = amount * fromRate / toRate;
        Bank.ConvertCurrencyResponse response = Bank.ConvertCurrencyResponse.newBuilder()
                .setCurrencyFrom(currencyFrom)
                .setCurrencyTo(currencyTo)
                .setAmount(amount)
                .setResult(result)
                .build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
    public void getStream(Bank.ConvertCurrencyRequest request, StreamObserver<Bank.ConvertCurrencyResponse> responseObserver) {
        String currencyFrom = request.getCurrencyFrom();
        String currencyTo = request.getCurrencyTo();
        double amount = request.getAmount();
        double fromRate = currencies.get(currencyFrom);
        double toRate = currencies.get(currencyTo);

        for (int i = 0; i < 5; i++) {
            // Simulating streaming by sending multiple responses
            if(i==1){ double result = amount * fromRate / toRate;
                Bank.ConvertCurrencyResponse response = Bank.ConvertCurrencyResponse.newBuilder()
                        .setCurrencyFrom(currencyFrom)
                        .setCurrencyTo(currencyTo)
                        .setAmount(amount)
                        .setResult(result)
                        .build();
                responseObserver.onNext(response);}

            int  result =  ThreadLocalRandom.current().nextInt(100000, 120039939);;
            Bank.ConvertCurrencyResponse response = Bank.ConvertCurrencyResponse.newBuilder()
                    .setCurrencyFrom(currencyFrom)
                    .setCurrencyTo(currencyTo)
                    .setAmount(amount)
                    .setResult(result)
                    .build();
            responseObserver.onNext(response);

            // Simulating delay between responses
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        responseObserver.onCompleted();
    }


    @Override
    public StreamObserver<Bank.ConvertCurrencyRequest> performStream(StreamObserver<Bank.ConvertCurrencyResponse> responseObserver) {
        return new StreamObserver<Bank.ConvertCurrencyRequest>() {
            double sum=0;
            @Override
            public void onNext(Bank.ConvertCurrencyRequest convertCurrencyRequest) {

                sum+=convertCurrencyRequest.getAmount();
            }

            @Override
            public void onError(Throwable throwable) {
                System.out.println("Error");
            }

            @Override
            public void onCompleted() {
                Bank.ConvertCurrencyResponse response=Bank.ConvertCurrencyResponse.newBuilder()
                                .setResult(sum)
                                        .build();
                responseObserver.onNext(response);
                responseObserver.onCompleted();
            }
        };
    }
    @Override
    public StreamObserver<Bank.ConvertCurrencyRequest> fullCurrencyStream(StreamObserver<Bank.ConvertCurrencyResponse> responseObserver) {
        return new StreamObserver<Bank.ConvertCurrencyRequest>() {
            double sum = 0;

            @Override
            public void onNext(Bank.ConvertCurrencyRequest convertCurrencyRequest) {
                // Process the incoming request, e.g., convert currency and accumulate the total.
                sum += convertCurrencyRequest.getAmount();

                // You can send intermediate responses if needed.
                Bank.ConvertCurrencyResponse intermediateResponse = Bank.ConvertCurrencyResponse.newBuilder()
                        .setResult(sum)
                        .build();
                responseObserver.onNext(intermediateResponse);
            }

            @Override
            public void onError(Throwable throwable) {
                System.out.println("Error");
            }

            @Override
            public void onCompleted() {
                // Final response with the total sum.
                Bank.ConvertCurrencyResponse finalResponse = Bank.ConvertCurrencyResponse.newBuilder()
                        .setResult(sum)
                        .build();
                responseObserver.onNext(finalResponse);

                // Mark the end of the response stream.
                responseObserver.onCompleted();
            }
        };
    }

}