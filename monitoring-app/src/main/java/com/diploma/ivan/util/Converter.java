package com.diploma.ivan.util;

import com.diploma.ivan.model.CombinedConfiguration;
import com.diploma.ivan.model.dto.ConditionDTO;
import com.diploma.ivan.model.dto.MailAction;
import com.diploma.ivan.model.dto.MonitoringConfigurationDTO;
import com.diploma.ivan.model.dto.UserDTO;
import com.diploma.ivan.model.entity.MailActionEntity;
import com.diploma.ivan.model.entity.ConditionEntity;
import com.diploma.ivan.model.entity.MonitoringConfigurationEntity;
import com.diploma.ivan.model.entity.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Converter {


    public static UserDTO convertToDto(User user) {
        return new UserDTO( //
                user.getName(), //
                user.getPassword(), //
                user.getRole() //
        );
    }

    public static MailAction convertToDto(MailActionEntity mailActionEntity) {
        return new MailAction( //
                mailActionEntity.getId(), //
                mailActionEntity.getDestination() //
        );
    }

    public static ConditionDTO convertToDto(ConditionEntity conditionEntity) {
        return new ConditionDTO( //
                conditionEntity.getId(), //
                conditionEntity.getPropertyKey(), //
                conditionEntity.getPropertyValue() //
        );
    }

    public static MonitoringConfigurationDTO convertToDto(MonitoringConfigurationEntity monitoringConfigurationEntity) {
        return new MonitoringConfigurationDTO( //
                monitoringConfigurationEntity.getId(), //
                monitoringConfigurationEntity.getName(), //
                monitoringConfigurationEntity.getBaseUrl(), //
                monitoringConfigurationEntity.getNamespace(), //
                monitoringConfigurationEntity.getDeployment(), //
                monitoringConfigurationEntity.getLabel(), //
                convertToDto(monitoringConfigurationEntity.getConditionEntity()), //
                convertToDto(monitoringConfigurationEntity.getActionEntity()) //
        );
    }

    public static User convertToEntity(UserDTO userDTO) {
        return new User( //
                userDTO.getName(), //
                userDTO.getPassword(), //
                userDTO.getRole() //
        );
    }

    public static MailActionEntity convertToEntity(MailAction actionDTO) {
        return new MailActionEntity( //
                actionDTO.getId(), //
                "EMAIL",
                actionDTO.getDestination() //
        );
    }

    public static ConditionEntity convertToEntity(ConditionDTO conditionDTO) {
        return new ConditionEntity( //
                conditionDTO.getId(), //
                conditionDTO.getPropertyKey(), //
                conditionDTO.getPropertyValue() //
        );
    }

    public static MonitoringConfigurationEntity convertToEntity(MonitoringConfigurationDTO monitoringConfigurationDTO) {
        return new MonitoringConfigurationEntity( //
                monitoringConfigurationDTO.getId(), //
                monitoringConfigurationDTO.getName(), //
                monitoringConfigurationDTO.getBaseUrl(), //
                monitoringConfigurationDTO.getNamespace(), //
                monitoringConfigurationDTO.getDeployment(), //
                monitoringConfigurationDTO.getLabel(), //
                convertToEntity(monitoringConfigurationDTO.getCondition()), //
                convertToEntity(monitoringConfigurationDTO.getAction()) //
        );
    }

    public static CombinedConfiguration convertMessageToCombinedConfiguration(String message) {
        ObjectMapper objectMapper = new ObjectMapper();
        CombinedConfiguration extractedData = null;
        try {
            extractedData = objectMapper.readValue(message, CombinedConfiguration.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return extractedData;
    }



}
