(ns material.icon
  (:refer-clojure :exclude [remove]))

(def home "M10 20v-6h4v6h5v-8h3L12 3 2 12h3v8z")
(def view-confy "M3 19h6v-7H3v7zm7 0h12v-7H10v7zM3 5v6h19V5H3z")
(def view-compact "M3 9h4V5H3v4zm0 5h4v-4H3v4zm5 0h4v-4H8v4zm5 0h4v-4h-4v4zM8 9h4V5H8v4zm5-4v4h4V5h-4zm5 9h4v-4h-4v4zM3 19h4v-4H3v4zm5 0h4v-4H8v4zm5 0h4v-4h-4v4zm5 0h4v-4h-4v4zm0-14v4h4V5h-4z")

;;; Menu icons

(def images "M20.25 0h-18c-1.237 0-2.25 1.013-2.25 2.25v19.5c0 1.237 1.013 2.25 2.25 2.25h18c1.237 0 2.25-1.013 2.25-2.25v-19.5c0-1.237-1.013-2.25-2.25-2.25zM19.5 21h-16.5v-18h16.5v18zM6 10.5h10.5v1.5h-10.5zM6 13.5h10.5v1.5h-10.5zM6 16.5h10.5v1.5h-10.5zM6 7.5h10.5v1.5h-10.5z")
(def stacks "M24 7.501l-12-6-12 6 12 6 12-6zM12 3.491l8.017 4.008-8.017 4.008-8.017-4.008 8.017-4.008zM21.597 10.798l2.403 1.202-12 6-12-6 2.403-1.202 9.597 4.798zM21.597 15.298l2.403 1.202-12 6-12-6 2.403-1.202 9.597 4.798z")
(def services "M6.352 20.12l4.235-2.117v-3.462l-4.235 1.809v3.772zM5.646 15.11l4.456-1.908-4.456-1.908-4.456 1.908zM17.648 20.12l4.235-2.117v-3.462l-4.235 1.809v3.772zM16.941 15.11l4.456-1.908-4.456-1.908-4.456 1.908zM12 11.878l4.235-1.82v-2.933l-4.235 1.809v2.944zM11.294 7.698l4.864-2.084-4.864-2.084-4.864 2.084zM23.296 13.412v4.588q0 0.398-0.21 0.739t-0.574 0.519l-4.941 2.47q-0.277 0.154-0.628 0.154t-0.628-0.154l-4.941-2.47q-0.056-0.022-0.078-0.044-0.022 0.022-0.078 0.044l-4.941 2.47q-0.277 0.154-0.628 0.154t-0.628-0.154l-4.941-2.47q-0.364-0.177-0.574-0.519t-0.21-0.739v-4.588q0-0.42 0.237-0.771t0.623-0.53l4.786-2.051v-4.412q0-0.42 0.237-0.771t0.623-0.53l4.941-2.117q0.254-0.111 0.552-0.111t0.552 0.111l4.941 2.117q0.386 0.177 0.623 0.53t0.237 0.771v4.412l4.786 2.051q0.398 0.177 0.628 0.53t0.232 0.771z")
(def tasks "M12 21.817l8.571-4.674v-8.518l-8.571 3.121v10.071zM11.143 10.232l9.348-3.402-9.348-3.402-9.348 3.402zM22.286 6.857v10.286q0 0.469-0.241 0.871t-0.656 0.629l-9.429 5.143q-0.375 0.214-0.817 0.214t-0.817-0.214l-9.429-5.143q-0.415-0.228-0.656-0.629t-0.241-0.871v-10.286q0-0.536 0.308-0.978t0.817-0.629l9.429-3.429q0.295-0.107 0.589-0.107t0.589 0.107l9.429 3.429q0.509 0.188 0.817 0.629t0.308 0.978z")
(def repositories "M1.714 18.857h13.714v-1.714h-13.714v1.714zM1.714 12h13.714v-1.714h-13.714v1.714zM22.714 18q0-0.536-0.375-0.911t-0.911-0.375-0.911 0.375-0.375 0.911 0.375 0.911 0.911 0.375 0.911-0.375 0.375-0.911zM1.714 5.143h13.714v-1.714h-13.714v1.714zM22.714 11.143q0-0.536-0.375-0.911t-0.911-0.375-0.911 0.375-0.375 0.911 0.375 0.911 0.911 0.375 0.911-0.375 0.375-0.911zM22.714 4.286q0-0.536-0.375-0.911t-0.911-0.375-0.911 0.375-0.375 0.911 0.375 0.911 0.911 0.375 0.911-0.375 0.375-0.911zM24 15.429v5.143h-24v-5.143h24zM24 8.571v5.143h-24v-5.143h24zM24 1.714v5.143h-24v-5.143h24z")
(def nodes "M20.002 3h-4.004C14.888 3 14 3.893 14 4.995V5h-4v-.005C10 3.893 9.105 3 8.002 3H3.998C2.888 3 2 3.893 2 4.995v14.01C2 20.107 2.895 21 3.998 21h4.004C9.112 21 10 20.107 10 19.005V7h4v12.005c0 1.102.895 1.995 1.998 1.995h4.004c1.11 0 1.998-.893 1.998-1.995V4.995C22 3.893 21.105 3 20.002 3zM5 11c-.552 0-1-.448-1-1s.448-1 1-1 1 .448 1 1-.448 1-1 1zm0-4c-.552 0-1-.448-1-1s.448-1 1-1 1 .448 1 1-.448 1-1 1zm12 4c-.552 0-1-.448-1-1s.448-1 1-1 1 .448 1 1-.448 1-1 1zm0-4c-.552 0-1-.448-1-1s.448-1 1-1 1 .448 1 1-.448 1-1 1z")
(def networks "M22.875 18h-0.375v-4.875c0-1.448-1.178-2.625-2.625-2.625h-6.375v-3h0.375c0.619 0 1.125-0.506 1.125-1.125v-3.75c0-0.619-0.506-1.125-1.125-1.125h-3.75c-0.619 0-1.125 0.506-1.125 1.125v3.75c0 0.619 0.506 1.125 1.125 1.125h0.375v3h-6.375c-1.448 0-2.625 1.178-2.625 2.625v4.875h-0.375c-0.619 0-1.125 0.506-1.125 1.125v3.75c0 0.619 0.506 1.125 1.125 1.125h3.75c0.619 0 1.125-0.506 1.125-1.125v-3.75c0-0.619-0.506-1.125-1.125-1.125h-0.375v-4.5h6v4.5h-0.375c-0.619 0-1.125 0.506-1.125 1.125v3.75c0 0.619 0.506 1.125 1.125 1.125h3.75c0.619 0 1.125-0.506 1.125-1.125v-3.75c0-0.619-0.506-1.125-1.125-1.125h-0.375v-4.5h6v4.5h-0.375c-0.619 0-1.125 0.506-1.125 1.125v3.75c0 0.619 0.506 1.125 1.125 1.125h3.75c0.619 0 1.125-0.506 1.125-1.125v-3.75c0-0.619-0.506-1.125-1.125-1.125zM4.5 22.5h-3v-3h3v3zM13.5 22.5h-3v-3h3v3zM10.5 6v-3h3v3h-3zM22.5 22.5h-3v-3h3v3z")
(def registries "M11.99 2C6.47 2 2 6.48 2 12s4.47 10 9.99 10C17.52 22 22 17.52 22 12S17.52 2 11.99 2zm6.93 6h-2.95c-.32-1.25-.78-2.45-1.38-3.56 1.84.63 3.37 1.91 4.33 3.56zM12 4.04c.83 1.2 1.48 2.53 1.91 3.96h-3.82c.43-1.43 1.08-2.76 1.91-3.96zM4.26 14C4.1 13.36 4 12.69 4 12s.1-1.36.26-2h3.38c-.08.66-.14 1.32-.14 2 0 .68.06 1.34.14 2H4.26zm.82 2h2.95c.32 1.25.78 2.45 1.38 3.56-1.84-.63-3.37-1.9-4.33-3.56zm2.95-8H5.08c.96-1.66 2.49-2.93 4.33-3.56C8.81 5.55 8.35 6.75 8.03 8zM12 19.96c-.83-1.2-1.48-2.53-1.91-3.96h3.82c-.43 1.43-1.08 2.76-1.91 3.96zM14.34 14H9.66c-.09-.66-.16-1.32-.16-2 0-.68.07-1.35.16-2h4.68c.09.65.16 1.32.16 2 0 .68-.07 1.34-.16 2zm.25 5.56c.6-1.11 1.06-2.31 1.38-3.56h2.95c-.96 1.65-2.49 2.93-4.33 3.56zM16.36 14c.08-.66.14-1.32.14-2 0-.68-.06-1.34-.14-2h3.38c.16.64.26 1.31.26 2s-.1 1.36-.26 2h-3.38z")
(def users "M12 2C6.48 2 2 6.48 2 12s4.48 10 10 10 10-4.48 10-10S17.52 2 12 2zm0 3c1.66 0 3 1.34 3 3s-1.34 3-3 3-3-1.34-3-3 1.34-3 3-3zm0 14.2c-2.5 0-4.71-1.28-6-3.22.03-1.99 4-3.08 6-3.08 1.99 0 5.97 1.09 6 3.08-1.29 1.94-3.5 3.22-6 3.22z")

(def trash "M6 19c0 1.1.9 2 2 2h8c1.1 0 2-.9 2-2V7H6v12zM19 4h-3.5l-1-1h-5l-1 1H5v2h14V4z")
(def minus "M12 2C6.48 2 2 6.48 2 12s4.48 10 10 10 10-4.48 10-10S17.52 2 12 2zm5 11H7v-2h10v2z")
(def plus "M12 2C6.48 2 2 6.48 2 12s4.48 10 10 10 10-4.48 10-10S17.52 2 12 2zm5 11h-4v4h-2v-4H7v-2h4V7h2v4h4v2z")
(def pen "M3 17.25V21h3.75L17.81 9.94l-3.75-3.75L3 17.25zM20.71 7.04c.39-.39.39-1.02 0-1.41l-2.34-2.34c-.39-.39-1.02-.39-1.41 0l-1.83 1.83 3.75 3.75 1.83-1.83z")
(def add "M19 13h-6v6h-2v-6H5v-2h6V5h2v6h6v2z")
(def remove "M19 13H5v-2h14v2z")
(def add-small "M13 7h-2v4H7v2h4v4h2v-4h4v-2h-4V7zm-1-5C6.48 2 2 6.48 2 12s4.48 10 10 10 10-4.48 10-10S17.52 2 12 2zm0 18c-4.41 0-8-3.59-8-8s3.59-8 8-8 8 3.59 8 8-3.59 8-8 8z")
(def remove-small "M7 11v2h10v-2H7zm5-9C6.48 2 2 6.48 2 12s4.48 10 10 10 10-4.48 10-10S17.52 2 12 2zm0 18c-4.41 0-8-3.59-8-8s3.59-8 8-8 8 3.59 8 8-3.59 8-8 8z")
(def expand "M16.59 8.59L12 13.17 7.41 8.59 6 10l6 6 6-6z")

(def left "M15.41 16.09l-4.58-4.59 4.58-4.59L14 5.5l-6 6 6 6z")
(def right "M8.59 16.34l4.58-4.59-4.58-4.59L10 5.75l6 6-6 6z")
(def create "M3 17.25V21h3.75L17.81 9.94l-3.75-3.75L3 17.25zM20.71 7.04c.39-.39.39-1.02 0-1.41l-2.34-2.34c-.39-.39-1.02-.39-1.41 0l-1.83 1.83 3.75 3.75 1.83-1.83z")
(def info "M11 17h2v-6h-2v6zm1-15C6.48 2 2 6.48 2 12s4.48 10 10 10 10-4.48 10-10S17.52 2 12 2zm0 18c-4.41 0-8-3.59-8-8s3.59-8 8-8 8 3.59 8 8-3.59 8-8 8zM11 9h2V7h-2v2z")
(def new "M20 4H4c-1.11 0-1.99.89-1.99 2L2 18c0 1.11.89 2 2 2h16c1.11 0 2-.89 2-2V6c0-1.11-.89-2-2-2zM8.5 15H7.3l-2.55-3.5V15H3.5V9h1.25l2.5 3.5V9H8.5v6zm5-4.74H11v1.12h2.5v1.26H11v1.11h2.5V15h-4V9h4v1.26zm7 3.74c0 .55-.45 1-1 1h-4c-.55 0-1-.45-1-1V9h1.25v4.51h1.13V9.99h1.25v3.51h1.12V9h1.25v5z")
(def wizard "M6 4.5l-3-3h-1.5v1.5l3 3zM7.5 0h1.5v3h-1.5zM13.5 7.5h3v1.5h-3zM15 3v-1.5h-1.5l-3 3 1.5 1.5zM0 7.5h3v1.5h-3zM7.5 13.5h1.5v3h-1.5zM1.5 13.5v1.5h1.5l3-3-1.5-1.5zM23.672 20.672l-14.909-14.909c-0.438-0.438-1.153-0.438-1.591 0l-1.409 1.409c-0.438 0.438-0.438 1.153 0 1.591l14.909 14.909c0.438 0.438 1.153 0.438 1.591 0l1.409-1.409c0.438-0.438 0.438-1.153 0-1.591zM11.25 12.75l-4.5-4.5 1.5-1.5 4.5 4.5-1.5 1.5z")

