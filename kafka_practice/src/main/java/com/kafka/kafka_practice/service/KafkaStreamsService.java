package com.kafka.kafka_practice.service;

import org.springframework.stereotype.Service;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.KTable;
import org.apache.kafka.streams.kstream.Produced;


import java.util.Properties;
import java.util.Arrays;

@Service
public class KafkaStreamsService {
//    Đọc dữ liệu từ topic input-topic.
//    Chia nhỏ câu thành từng từ (flatMapValues).
//    Gom nhóm và đếm số lần xuất hiện của từng từ (groupBy().count()).
//    Ghi kết quả ra topic output-topic.
//    Ứng dụng này giúp đếm số lần xuất hiện của từng từ trong real-time.
    public void startWordCountStream() {
        // Cấu hình Kafka Streams
        Properties props = new Properties();
        props.put(StreamsConfig.APPLICATION_ID_CONFIG, "word-count-app");
        props.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        props.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass());
        props.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, Serdes.String().getClass());

        StreamsBuilder builder = new StreamsBuilder();

        // Lấy dữ liệu từ topic "input-topic"
        KStream<String, String> textLines = builder.stream("input-topic");

        // Xử lý đếm số lần xuất hiện của từng từ
        KTable<String, Long> wordCounts = textLines
                .flatMapValues(value -> Arrays.asList(value.toLowerCase().split("\\W+")))
                .groupBy((key, word) -> word)
                .count();

        // Ghi kết quả vào "output-topic"
        wordCounts.toStream().to("output-topic", Produced.with(Serdes.String(), Serdes.Long()));

        KafkaStreams streams = new KafkaStreams(builder.build(), props);
        streams.start();

        Runtime.getRuntime().addShutdownHook(new Thread(streams::close));
    }
}