package com.diploma.ivan.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Metric implements Serializable {

    private String kind;
    private List<Pod> items;

    public Metric() {
        
    }
    
    public Metric(String kind, List<Pod> items) {
        this.kind = kind;
        this.items = items;
    }

    public String getKind() {
        return kind;
    }

    public List<Pod> getItems() {
        return items;
    }

    public static class Pod implements Serializable {


        private Metadata metadata;

        private List<Container> containers;

        public Pod() {

        }

        public Pod(Metadata metadata, List<Container> containers) {
            this.metadata = metadata;
            this.containers = containers;
        }

        public Metadata getMetadata() {
            return metadata;
        }

        public List<Container> getContainers() {
            return containers;
        }

        public static class Metadata implements Serializable {
            private String name;


            public Metadata() {

            }

            public Metadata(String names) {
                this.name = name;
            }

            public String getName() {
                return name;
            }

        }

        public static class Container implements Serializable {
            private String name;
            private Usage usage;

            public Container() {

            }

            public Container(String name, Usage usage) {
                this.name = name;
                this.usage = usage;
            }

            public String getName() {
                return name;
            }

            public Usage getUsage() {
                return usage;
            }

            public static class Usage implements Serializable{
                private String cpu;
                private String memory;

                public Usage() {

                }

                public Usage(String cpu, String memory) {
                    this.cpu = cpu;
                    this.memory = memory;
                }

                public String getCpu() {
                    return cpu;
                }

                public String getMemory() {
                    return memory;
                }
            }
        }
    }
}
