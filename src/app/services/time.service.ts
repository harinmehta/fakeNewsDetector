import { Injectable } from '@angular/core';

@Injectable()
export class TimeService {

  constructor() { }

  /* timePassed(i: string) {
    const writtenDate = new Date(parseInt(i, 10) * 1000);
    const presentDate = new Date();
    if (writtenDate.getFullYear() === presentDate.getFullYear()) {
      if (writtenDate.getMonth() === presentDate.getMonth() || writtenDate.getDate() > presentDate.getDate()) {
        if (writtenDate.getDate() === presentDate.getDate()) {
            if (writtenDate.getHours() === presentDate.getHours()) {
              if (writtenDate.getMinutes() === presentDate.getMinutes()) {
                if (writtenDate.getSeconds() === presentDate.getSeconds()) {
                  return 'Just Now';
                }else {
                  return presentDate.getSeconds() - writtenDate.getSeconds() + ' sec ago';
                }
              }else {
                return presentDate.getMinutes() - writtenDate.getMinutes() + ' min ago';
              }
            }else {
              return presentDate.getHours() - writtenDate.getHours() + ' hrs ago';
            }
        }else {
          let date = (presentDate.getDate() - writtenDate.getDate());
          if (date < 0) {
            date += 30;
          }
          return date + ' day ago';
        }
      }else {
        return presentDate.getMonth() - writtenDate.getMonth() + ' month ago';
      }
    }else {
      return presentDate.getFullYear() - writtenDate.getFullYear() + ' year ago';
    }
  } */

  timePassed(i: string) {
    const writtenDate = new Date(parseInt(i, 10) * 1000);
    const presentDate = new Date();
    if (writtenDate.getDate() === presentDate.getDate() &&
        writtenDate.getMonth() === presentDate.getMonth() &&
        writtenDate.getFullYear() === presentDate.getFullYear()) {
          if (writtenDate.getHours() === presentDate.getHours()) {
            if (writtenDate.getMinutes() === presentDate.getMinutes()) {
              if (writtenDate.getSeconds() === presentDate.getSeconds()) {
                return 'Just Now';
              }else {
                return presentDate.getSeconds() - writtenDate.getSeconds() + ' sec ago';
              }
            }else {
              return presentDate.getMinutes() - writtenDate.getMinutes() + ' min ago';
            }
          }else {
            return presentDate.getHours() - writtenDate.getHours() + ' hrs ago';
          }
    }else {
      return this.ExactDate(parseInt(i, 10));
    }
  }

  ExactDate(i: number) {
    const writtenDate = new Date(i * 1000);
    return  writtenDate.toDateString();
  }

  timeToRead(s: string) {
    const words = s.split(' ');
    const time = Math.round(words.length / 180);
    if (time > 1) {
      return time + ' min read';
    }else {
      return '2 min read';
    }
  }

  getDate(i: string) {
    // console.log(i);
    const commentDate = new Date(parseInt(i, 10) * 1000);
    const presentDate = new Date();
      if (commentDate.getFullYear() === presentDate.getFullYear()) {
      if (commentDate.getMonth() === presentDate.getMonth()) {
         if (presentDate.getDate() === commentDate.getDate()) {
           if (presentDate.getHours() === commentDate.getHours()) {
              if (presentDate.getMinutes() === commentDate.getMinutes()) {
                if (presentDate.getSeconds() === commentDate.getSeconds()) {
                  return 'Just Now';
                } else {
                  return presentDate.getSeconds() - commentDate.getSeconds() + ' s';
                }
              } else {
                return presentDate.getMinutes() - commentDate.getMinutes() + ' min';
              }
            }else {
              presentDate.getHours() - commentDate.getHours() +  ' h';
            }
          }else {
            return presentDate.getDate() - commentDate.getDate() + ' d';
          }
      } else {
        return presentDate.getMonth() - commentDate.getMonth() + ' month';
      }
    } else {
      return presentDate.getFullYear() - commentDate.getFullYear() + ' yr';
    }

  }

}
