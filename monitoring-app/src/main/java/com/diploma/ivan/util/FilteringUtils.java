package com.diploma.ivan.util;

import com.diploma.ivan.model.DeliveryObject;
import com.diploma.ivan.model.Metric;
import com.diploma.ivan.model.StorageFormat;
import com.diploma.ivan.model.entity.ConditionEntity;
import com.google.common.collect.ImmutableList;

import java.util.List;

public class FilteringUtils {

    public static DeliveryObject checkIfCrossesThreshold(String podName, List<Metric.Pod.Container> containers, ConditionEntity condition, String namespace, String deployment, String url) {
        for(Metric.Pod.Container container : containers) {
            if (condition.getPropertyKey().equals("cpu")) {
                int actualValue = convertCPU(container.getUsage().getCpu());
                int conditionValue = convertCPU(condition.getPropertyValue());

                if (actualValue > conditionValue) {
                    return new DeliveryObject(podName, container.getUsage().getCpu(), condition.getPropertyValue(), namespace, deployment, url);
                }
            } else if (condition.getPropertyKey().equals("memory")) {
                List<Integer> actualValues = convertMemory(container.getUsage().getMemory());
                List<Integer> conditionValues = convertMemory(condition.getPropertyValue());

                if (actualValues.get(1) >= conditionValues.get(1) && actualValues.get(0) > conditionValues.get(0)) {
                    return new DeliveryObject(podName, container.getUsage().getMemory(), condition.getPropertyValue(), namespace, deployment, url);
                }
            }
        }

        return null;
    }

    private static int convertCPU(String cpu) {
        return Integer.parseInt(cpu.replace("n", ""));
    }

    private static List<Integer> convertMemory(String memory) {
        String unit = memory.substring(memory.length() - 2);
        String memoryConverted = memory.substring(0, memory.length() - 2);

        return ImmutableList.<Integer>builder()
                .add(Integer.parseInt(memoryConverted))
                .add(StorageFormat.SizeMap.get(StorageFormat.valueOf(unit)))
                .build();
    }
}
