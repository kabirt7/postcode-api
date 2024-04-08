import { createContext, useState, ReactNode } from "react";

interface ToastContextProps {
  message: string | null;
  setMessage: React.Dispatch<any>;
}

export const ToastContext = createContext<ToastContextProps>({
  message: null,
  setMessage: () => {},
});

const ToastContextProvider = ({ children }: { children: ReactNode }) => {
  const [message, setMessage] = useState<string | null>(null);

  return (
    <ToastContext.Provider value={{ message, setMessage }}>
      {children}
    </ToastContext.Provider>
  );
};

export default ToastContextProvider;
