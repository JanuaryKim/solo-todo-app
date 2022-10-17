package code.solo.todoapp.utils;

import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.util.Collection;

@Component
public class CustomUtils <T> {

    public T copyNotnullProperties(T source, T dest)
    {
        if(source == null || dest == null)
        {
            throw new RuntimeException("잘못된 인풋");
        }


        BeanWrapper sourceWrapper = new BeanWrapperImpl(source);
        BeanWrapper destWrapper = new BeanWrapperImpl(dest);

        for (Field field : dest.getClass().getDeclaredFields()) {

            String name = field.getName();

            Object propertyValue = destWrapper.getPropertyValue(name);

            if (propertyValue != null && !(propertyValue instanceof Collection)) {
                sourceWrapper.setPropertyValue(name, propertyValue);
            }
        }

        return source;

    }
}
